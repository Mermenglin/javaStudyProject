package com.ThreadCount.demo.filter;

import java.io.IOException;

public class TomcatResponseWrap implements IHttpResponse {
    private WrapperResponse response;

    private TomcatResponseWrap(WrapperResponse response) {
        this.response = response;
    }

    public String content() {
        try {
            return new String(this.response.getResponseData());
        } catch (IOException var2) {
            return "";
        }
    }

    public long contentLength() {
        return (long)this.response.getBufferSize();
    }

    public int statusCode() {
        return this.response.getStatus();
    }

    public String getHeader(String name) {
        return this.response.getHeader(name);
    }

    public static IHttpResponse get(WrapperResponse response) {
        return new TomcatResponseWrap(response);
    }
}
