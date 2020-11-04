package com.family.tree.lambda.functions.getOps;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.family.tree.dynamoDB.DAO.GetDAO;
import com.family.tree.dynamoDB.guice.DynamoDBModule;
import com.family.tree.dynamoDB.table.mapping.ReplyPOJO;
import com.family.tree.lambda.functions.getOps.replys.GetHttpResponse;
import com.family.tree.lambda.functions.getOps.requests.GetHttpRequest;
import com.family.tree.lambda.functions.putOps.FamilyTreeSetters;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GetProfilesByTypeLambda extends FamilyTreeSetters implements RequestHandler<GetHttpRequest, GetHttpResponse> {
	private GetDAO getDAO;
	
	public GetProfilesByTypeLambda() {
		final Injector injector = Guice.createInjector(new DynamoDBModule());
		this.getDAO = injector.getInstance(GetDAO.class);
	}
	
    @Override
    public GetHttpResponse handleRequest(GetHttpRequest request, Context context) {
        context.getLogger().log("request: " + request);
        List<ReplyPOJO> result = new ArrayList<ReplyPOJO>();
        
        if(request.getQueryStringParameters().get("gettype").equals(GetTypeEnum.BYFIRSTNAME.getValue())) {
        	 
        	result = getDAO.getProfileByName(request.getQueryStringParameters().get("firstname"));	
        	 
        } else if(request.getQueryStringParameters().get("gettype").equals(GetTypeEnum.BYID.getValue())){
        	
        	result = getDAO.getProfileById(request.getQueryStringParameters().get("id"));
        	
        } else if(request.getQueryStringParameters().get("gettype").equals(GetTypeEnum.BYCHILDID.getValue())) {
        	
        	result = getDAO.getParentsInfoByChildId(request.getQueryStringParameters().get("childid"));
        	
        } else if(request.getQueryStringParameters().get("gettype").equals(GetTypeEnum.BYPARENTID.getValue())) {
        	
        	result = getDAO.getChildrensByParentId(request.getQueryStringParameters().get("parentid"));
        	
        }
        
        return new GetHttpResponse(result);
    }
    
}
