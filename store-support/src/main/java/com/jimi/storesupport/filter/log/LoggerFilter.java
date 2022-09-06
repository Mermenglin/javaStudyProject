package com.jimi.onlinestoresupport.filter.log;

import com.jimi.onlinestoresupport.util.RandomUtil;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("all")
public class LoggerFilter implements Filter {

    private final static String SESSION_ID = "sessionId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uuid = RandomUtil.getUuid();
        MDC.put(SESSION_ID, uuid);

        WrapperResponse wrapperResponse = new WrapperResponse(httpResponse);
        HttpServletRequest wrapperRequest = new WrapperRequest(httpRequest, true);
        LoggerService.printRequest(wrapperRequest, wrapperResponse);
        chain.doFilter(wrapperRequest, wrapperResponse);
        LoggerService.printResponse(wrapperRequest, wrapperResponse);
        MDC.remove(SESSION_ID);
    }

    @Override
    public void destroy() {

    }
}
