package com.ThreadCount.demo.filter;

public interface IHttpResponse {
    String content();

    long contentLength();

    int statusCode();

    String getHeader(String var1);
}
