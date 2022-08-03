package com.ThreadCount.demo.filter;

import java.util.Map;

public class LoggerVo {
    private String reqId;
    private String ip;
    private String url;
    private Map<String, String> params;
    private Long start;
    private long executeTime = -1L;
    private Object result;

    public LoggerVo() {
    }

    public LoggerVo(String reqId) {
        this.reqId = reqId;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Long getStart() {
        return this.start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public long getExecuteTime() {
        return this.executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    public String getReqId() {
        return this.reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {
        return this.result instanceof WrapperResponse ? "{ip=" + this.ip + ",url=" + this.url + ",params=" + this.params + ",cost=" + this.executeTime + "}" : "{ip=" + this.ip + ",url=" + this.url + ",params=" + this.params + ",cost=" + this.executeTime + ",result=" + this.result + "}";
    }
}
