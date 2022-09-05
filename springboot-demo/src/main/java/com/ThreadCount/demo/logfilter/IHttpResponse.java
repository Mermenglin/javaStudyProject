package com.ThreadCount.demo.logfilter;

public interface IHttpResponse {
    String content();

    long contentLength();

    int statusCode();

    String getHeader(String var1);
}
