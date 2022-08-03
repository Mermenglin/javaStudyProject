package com.ThreadCount.demo.filter;

public interface IHttpRequest {
    String localHost();

    int localPort();

    String remoteHost();

    int remotePort();

    String method();

    int contentLength();

    String getHeader(String var1);

    String path();

    String query();
}
