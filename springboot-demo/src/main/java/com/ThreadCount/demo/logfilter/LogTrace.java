package com.ThreadCount.demo.logfilter;

import org.slf4j.Logger;

import java.util.UUID;

public class LogTrace {
    private static final ThreadLocal<LogTraceEntity> logVo = new ThreadLocal();
    private static String logAppName = "default-trace-app";
    private static Logger logger;

    public LogTrace() {
    }

    public static void init(String appName, Logger logObj) {
        logAppName = appName;
        logger = logObj;
    }

    public static void startHttp(IHttpRequest request) {
        String serverIp = request.localHost();
        int serverPort = request.localPort();
        LogTraceEntity log = new LogTraceEntity();
        log.setRequestTime(System.currentTimeMillis());
        log.setAppName(logAppName);
        log.setClientIp(getClientIp(request));
        log.setClientPort(request.remotePort());
        log.setServerIp(serverIp);
        log.setServerPort(serverPort);
        log.setMethod(request.method());
        long requestLen = (long)request.contentLength();
        log.setRequestLength(requestLen);
        String traceId = request.getHeader("mc-trace-id");
        String tracePath = request.getHeader("mc-trace-path");
        if (traceId != null && !traceId.isEmpty()) {
            tracePath = tracePath + "$";
        } else {
            traceId = UUID.randomUUID().toString().replace("-", "");
            tracePath = "";
        }

        tracePath = String.format("%s%s:%s:%s:%s", tracePath, System.currentTimeMillis(), logAppName, serverIp, serverPort);
        log.setTraceId(traceId);
        log.setTracePath(tracePath);
        log.setUrl(request.path());
        log.setQueryParams(request.query());
        logVo.set(log);
    }

    public static void stopHttp(IHttpResponse response) {
        LogTraceEntity log = (LogTraceEntity)logVo.get();
        if (log != null) {
            log.setElapsedTime(System.currentTimeMillis() - log.getRequestTime());
            log.setStatusCode(response.statusCode());
            log.setResponseLength(response.contentLength());
            String content = response.content();
            if (content != null && !content.isEmpty()) {
                if (content.length() > 300) {
                    content = content.substring(0, 300);
                }

                log.setReturnContent(content);
            }

            if (logger != null) {
                logger.info(log.toString());
            }

            logVo.remove();
        }
    }

    private static String getClientIp(IHttpRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        ip = getIpFromHeaderIfNotAlreadySet("X-Real-IP", request, ip);
        ip = getIpFromHeaderIfNotAlreadySet("Proxy-Client-IP", request, ip);
        ip = getIpFromHeaderIfNotAlreadySet("WL-Proxy-Client-IP", request, ip);
        ip = getIpFromHeaderIfNotAlreadySet("HTTP_CLIENT_IP", request, ip);
        ip = getIpFromHeaderIfNotAlreadySet("HTTP_X_FORWARDED_FOR", request, ip);
        ip = getFirstIp(ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "127.0.0.1".equalsIgnoreCase(ip)) {
            ip = request.remoteHost();
        }

        return ip;
    }

    private static String getFirstIp(String ip) {
        if (ip != null) {
            int indexOfFirstComma = ip.indexOf(44);
            if (indexOfFirstComma != -1) {
                ip = ip.substring(0, indexOfFirstComma);
            }
        }

        return ip;
    }

    private static String getIpFromHeaderIfNotAlreadySet(String header, IHttpRequest request, String ip) {
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader(header);
        }

        return ip;
    }

    public static LogTraceEntity get() {
        return (LogTraceEntity)logVo.get();
    }

    public static void putData(String key, String val) {
        LogTraceEntity log = (LogTraceEntity)logVo.get();
        if (log != null && key != null && !key.isEmpty() && val != null && !val.isEmpty()) {
            log.pubQueryData(key, val);
        }

    }

    public static String getData(String key) {
        LogTraceEntity log = (LogTraceEntity)logVo.get();
        return log != null ? log.getQueryData(key) : "";
    }

    public static void setCallMethod(String callMethod) {
        LogTraceEntity log = (LogTraceEntity)logVo.get();
        if (log != null) {
            log.setCallMethod(callMethod);
        }

    }

    public static String getTraceId() {
        LogTraceEntity log = (LogTraceEntity)logVo.get();
        return log != null ? log.getTraceId() : "";
    }

    public static String getTracePath() {
        LogTraceEntity log = (LogTraceEntity)logVo.get();
        return log != null ? log.getTracePath() : "";
    }

    public static void setBusinessCode(String businessCode) {
        LogTraceEntity log = (LogTraceEntity)logVo.get();
        if (log != null) {
            log.setBusinessCode(businessCode);
        }

    }

    static {
        String tmp = System.getProperty("java.class.path");
        if (tmp != null && !tmp.isEmpty()) {
            String[] ary = tmp.split("/");
            logAppName = ary[ary.length - 1];
        }

    }
}
