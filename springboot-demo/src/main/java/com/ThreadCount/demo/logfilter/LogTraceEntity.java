package com.ThreadCount.demo.logfilter;

import java.util.HashMap;
import java.util.Map;

public class LogTraceEntity {
    private Long requestTime;
    private String appName;
    private String traceId;
    private String tracePath;
    private final Map<String, String> queryData = new HashMap();
    private String clientIp;
    private int clientPort;
    private String serverIp;
    private int serverPort;
    private Long elapsedTime;
    private Long requestLength;
    private Long responseLength;
    private String method;
    private String url;
    private int statusCode;
    private String queryParams;
    private String callMethod;
    private String businessCode;
    private String returnContent;

    public LogTraceEntity() {
    }

    public Long getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTracePath() {
        return this.tracePath;
    }

    public void setTracePath(String tracePath) {
        this.tracePath = tracePath;
    }

    public String getQueryData() {
        StringBuilder sb = new StringBuilder();
        this.queryData.forEach((key, value) -> {
            sb.append(String.format("%s:%s$", this.formatStr(key), this.formatStr(value)));
        });
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }

        return sb.toString();
    }

    public String getQueryData(String key) {
        return (String)this.queryData.get(key);
    }

    private String formatStr(String val) {
        return val.replaceAll("\\s", "").replaceAll("\\|", "@{124}").replaceAll("\\#", "@{35}").replaceAll("\\:", "@{58}").replaceAll("\\$", "@{36}");
    }

    public Map<String, String> pubQueryData(String key, String queryData) {
        this.queryData.put(key, queryData);
        return this.queryData;
    }

    public String getClientIp() {
        return this.clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public int getClientPort() {
        return this.clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public String getServerIp() {
        return this.serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getServerPort() {
        return this.serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public Long getElapsedTime() {
        return this.elapsedTime;
    }

    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Long getRequestLength() {
        return this.requestLength;
    }

    public void setRequestLength(Long requestLength) {
        this.requestLength = requestLength;
    }

    public Long getResponseLength() {
        return this.responseLength;
    }

    public void setResponseLength(Long responseLength) {
        this.responseLength = responseLength;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getQueryParams() {
        return this.queryParams;
    }

    public void setQueryParams(String queryParams) {
        this.queryParams = queryParams;
    }

    public String getCallMethod() {
        return this.callMethod;
    }

    public void setCallMethod(String callMethod) {
        this.callMethod = callMethod;
    }

    public String getBusinessCode() {
        return this.businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getReturnContent() {
        return this.returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
    }

    public String toString() {
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s", this.getRequestTime(), this.getAppName(), this.getTraceId(), this.getTracePath(), this.getQueryData(), this.getClientIp(), this.getClientPort(), this.getServerIp(), this.getServerPort(), this.getElapsedTime(), this.getRequestLength(), this.getResponseLength(), this.getMethod(), this.getUrl(), this.getStatusCode(), this.getQueryParams(), this.getCallMethod(), this.getBusinessCode(), this.getReturnContent());
    }
}
