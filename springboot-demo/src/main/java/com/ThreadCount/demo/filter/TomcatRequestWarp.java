package com.ThreadCount.demo.filter;

import javax.servlet.http.HttpServletRequest;

public class TomcatRequestWarp implements IHttpRequest {
    private HttpServletRequest request;

    private TomcatRequestWarp(HttpServletRequest request) {
        this.request = request;
    }

    public String localHost() {
        return this.request.getLocalAddr();
    }

    public int localPort() {
        return this.request.getLocalPort();
    }

    public String remoteHost() {
        return this.request.getRemoteAddr();
    }

    public int remotePort() {
        return this.request.getRemotePort();
    }

    public String method() {
        return this.request.getMethod();
    }

    public int contentLength() {
        return this.request.getContentLength();
    }

    public String getHeader(String name) {
        return this.request.getHeader(name);
    }

    public String path() {
        return this.request.getRequestURI();
    }

    public String query() {
        return this.request.getQueryString();
    }

    public static IHttpRequest get(HttpServletRequest request) {
        return new TomcatRequestWarp(request);
    }
}