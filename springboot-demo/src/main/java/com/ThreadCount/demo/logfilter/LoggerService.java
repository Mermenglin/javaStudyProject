package com.ThreadCount.demo.logfilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.nio.charset.*;
public class LoggerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerService.class);
    private static final String LOGVO_KEY = "msart.request.logVo";
    private static final String REQ_ID = "reqId";
    private static final Set<String> SKIP_PARAMS = new HashSet(Collections.singletonList("pic"));
    private static final Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r|\\s+|\n\t)");

    public LoggerService() {
    }

    public static LoggerVo getParameter(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        LoggerVo loggerVo = new LoggerVo();
        loggerVo.setUrl(httpRequest.getRequestURL().toString());
        Map<String, String> params = new HashMap();
        Enumeration enu = httpRequest.getParameterNames();

        String reqId;
        while(enu.hasMoreElements()) {
            String paramName = (String)enu.nextElement();
            if (!SKIP_PARAMS.contains(paramName)) {
                reqId = httpRequest.getParameter(paramName);
                params.put(paramName, reqId);
                if ("password".equalsIgnoreCase(paramName)) {
                    params.put(paramName, "*****");
                }
            }
        }

        WrapperRequest wrapperRequest = (WrapperRequest)httpRequest;
        if (wrapperRequest.isPrintBody()) {
            try {
                if (wrapperRequest.getInput() != null) {
                    reqId = CRLF.matcher(new String(wrapperRequest.getInput(), StandardCharsets.UTF_8)).replaceAll("");
                    if (JsonUtil.isJsonObj(reqId)) {
                        Map<String, Object> node = JsonUtil.parseMap(reqId, String.class, Object.class);
                        if (node.containsKey("reqId")) {
                            loggerVo.setReqId(String.valueOf(node.get("reqId")));
                        }
                    }

                    params.put("body", reqId);
                }

                if (Objects.isNull(loggerVo.getReqId())) {
                    reqId = wrapperRequest.getParameter("reqId");
                    if (Objects.isNull(reqId)) {
                        reqId = wrapperRequest.getHeader("reqId");
                    }

                    loggerVo.setReqId(reqId);
                }
            } catch (IOException var11) {
                LOGGER.error("读取request输入流异常", var11);
            }
        }

        loggerVo.setParams(params);
        reqId = httpRequest.getRemoteAddr();
        String forwarded = httpRequest.getHeader("X-Forwarded-For");
        String realIp = httpRequest.getHeader("X-Real-IP");
        String ip = "";
        if (realIp == null) {
            if (forwarded == null) {
                ip = reqId;
            } else {
                int index = forwarded.indexOf(",");
                if (index != -1) {
                    ip = forwarded.substring(0, index);
                }
            }
        } else {
            ip = realIp;
        }

        loggerVo.setIp(ip);
        return loggerVo;
    }

    public static void printRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        try {
            LoggerVo loggerVo = getParameter(httpRequest, httpResponse);
            loggerVo.setStart(System.currentTimeMillis());
            httpRequest.setAttribute("msart.request.logVo", loggerVo);
            LogTrace.putData("reqId", loggerVo.getReqId());
            LOGGER.info(">>>ReceiveHttpReq:reqId=" + loggerVo.getReqId() + " sid=" + LogTrace.getTraceId() + "method= " + httpRequest.getMethod() + " " + loggerVo.toString());
        } catch (Exception var4) {
            LOGGER.error("request print Exception=" + var4.getMessage(), var4);
        }

    }

    public static void printResponse(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        try {
            LoggerVo loggerVo = (LoggerVo)httpRequest.getAttribute("msart.request.logVo");
            WrapperResponse wrapperResponse = (WrapperResponse)httpResponse;
            byte[] data = wrapperResponse.getResponseData();
            String result = new String(data);
            loggerVo.setExecuteTime(System.currentTimeMillis() - loggerVo.getStart());
            loggerVo.setResult(result);
            LOGGER.info("<<<CompleteHttpReq:reqId=" + loggerVo.getReqId() + " sid=" + LogTrace.getTraceId() + " " + loggerVo.toString());
        } catch (Exception var6) {
            LOGGER.error("reponse print Exception=" + var6.getMessage(), var6);
        }

    }
}
