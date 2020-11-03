package com.family.tree.lambda.functions.getOps.requests;

import java.util.Map;

public class GetHttpRequest {
	
	Map<String,String> queryStringParameters;
	
	public Map<String, String> getQueryStringParameters() {
		return queryStringParameters;
	}

	public void setQueryStringParameters(Map<String, String> queryStringParameters) {
		this.queryStringParameters = queryStringParameters;
	}
}
