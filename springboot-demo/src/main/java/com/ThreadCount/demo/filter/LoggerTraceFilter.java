package com.ThreadCount.demo.filter;

import com.util.RandomUtil;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("all")
public class LoggerTraceFilter implements Filter {

    private WebApplicationContext wac;

    private final static String SESSION_ID = "sessionId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        wac = (WebApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

        String appName = wac.getEnvironment().getProperty("spring.application.name");
        LogTrace.init(appName, LoggerFactory.getLogger("logTraceAppender"));
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
        LogTrace.startHttp(TomcatRequestWarp.get(wrapperRequest));
        LoggerService.printRequest(wrapperRequest, wrapperResponse);
        chain.doFilter(wrapperRequest, wrapperResponse);
        LoggerService.printResponse(wrapperRequest, wrapperResponse);
        LogTrace.stopHttp(TomcatResponseWrap.get(wrapperResponse));
        MDC.remove(SESSION_ID);
    }

    @Override
    public void destroy() {

    }
}
