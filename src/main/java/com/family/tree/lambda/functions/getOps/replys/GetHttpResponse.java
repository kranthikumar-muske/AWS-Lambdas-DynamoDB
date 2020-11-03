package com.family.tree.lambda.functions.getOps.replys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class GetHttpResponse {
	private String body;
	private String statusCode = "200";
	private Map<String, String> headers = new HashMap<String, String>();
	
	public GetHttpResponse() {
		super();
		this.headers.put("Content-Type", "application/json");
		this.headers.put("Access-Control-Allow-Origin", "*");
		this.headers.put("Access-Control-Allow-Credentials", "true");
	}
	
	public GetHttpResponse(List<com.family.tree.dynamoDB.table.mapping.ReplyPOJO> familyTreeTablePOJO) {
		this();
		Gson gson = new Gson();
		this.body = gson.toJson(familyTreeTablePOJO);
	}
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	
}
