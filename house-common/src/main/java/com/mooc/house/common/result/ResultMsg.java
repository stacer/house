package com.mooc.house.common.result;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

public class ResultMsg {
	
	public static final String errorMsgKey = "errorMsg";
	public static final String successMsgKey = "successMsg";
	
	public static ResultMsg errorMsg(String msg) {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setErrorMsg(msg);
		return resultMsg;
	}
	
	public static ResultMsg successMsg(String msg) {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setSuccessMsg(msg);
		return resultMsg;
	}
	
	public Map<String, String> asMap(){
		HashMap<String, String> map = Maps.newHashMap();
		map.put(successMsgKey, successMsg);
		map.put(errorMsgKey, errorMsg);
		return map;
	}
	
	public boolean isSuccess() {
		return errorMsg == null;
	}
	
	public String asUrlParams() {
		Map<String, String> map= asMap();
		HashMap<String, String> newHashMap = Maps.newHashMap();
		map.forEach((k,v) -> {
			if(v!=null) {
				try {
					newHashMap.put(k,URLEncoder.encode(v, "utf-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return Joiner.on("&").useForNull("").withKeyValueSeparator("=").join(newHashMap);
	}
	
	private String errorMsg;
	private String successMsg;
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	
	
}
