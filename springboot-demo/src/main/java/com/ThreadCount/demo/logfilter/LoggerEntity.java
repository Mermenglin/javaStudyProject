package com.ThreadCount.demo.logfilter;

import java.util.Arrays;

public class LoggerEntity {

	private String reqId;

	private String method;
	
	private Object[] args;

	private String url;

	private String callMethod;

	private String returnContent;

	private long costTime;
	
	private long beginTime;

	/**
	 * @return the beginTime
	 */
	public long getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return the reqId
	 */
	public String getReqId() {
		return reqId;
	}

	/**
	 * @param reqId the reqId to set
	 */
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	

	/**
	 * @return the args
	 */
	public Object[] getArgs() {
		return args;
	}

	/**
	 * @param args the args to set
	 */
	public void setArgs(Object[] args) {
		this.args = args;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the callMethod
	 */
	public String getCallMethod() {
		return callMethod;
	}

	/**
	 * @param callMethod the callMethod to set
	 */
	public void setCallMethod(String callMethod) {
		this.callMethod = callMethod;
	}


	/**
	 * @return the returnContent
	 */
	public String getReturnContent() {
		return returnContent;
	}

	/**
	 * @param returnContent the returnContent to set
	 */
	public void setReturnContent(String returnContent) {
		this.returnContent = returnContent;
	}

	/**
	 * @return the costTime
	 */
	public long getCostTime() {
		return costTime;
	}

	/**
	 * @param costTime the costTime to set
	 */
	public void setCostTime(long costTime) {
		this.costTime = costTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.format(
				"LoggerEntity [reqId=%s, method=%s, args=%s, url=%s, callMethod=%s, returnContent=%s, costTime=%s]",
				reqId, method, Arrays.toString(args), url, callMethod, returnContent, costTime);
	}

}
