package com.family.tree.lambda.functions.putOps;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.family.tree.dynamoDB.DAO.PutDAO;
import com.family.tree.dynamoDB.guice.DynamoDBModule;
import com.family.tree.dynamoDB.util.GenerateRandomUUID;
import com.family.tree.lambda.functions.putOps.replys.PutHttpResponse;
import com.family.tree.lambda.functions.putOps.requests.PutChildHttpRequest;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class AddChildLambda extends FamilyTreeSetters implements RequestHandler<PutChildHttpRequest, PutHttpResponse> {
	private PutDAO putDAO;
	private GenerateRandomUUID generateRandomUUID;

	
	public AddChildLambda() {
		final Injector injector = Guice.createInjector(new DynamoDBModule());
		this.putDAO = injector.getInstance(PutDAO.class);
		this.generateRandomUUID = injector.getInstance(GenerateRandomUUID.class);
	}
	
    @Override
    public PutHttpResponse handleRequest(PutChildHttpRequest request, Context context) {
        context.getLogger().log("Input: " + request);

        String childUserId = generateRandomUUID.buildRandomUUID();
        String fatherUserId = request.getFatherUserId();
        String motherUserId = request.getMotherUserId();
        
        putDAO.createNewChildAndlinkToParents(setChildProfile(request, childUserId), linkChildToFather(childUserId, fatherUserId),linkChildToMother(childUserId, motherUserId));
        
        return new PutHttpResponse();
    }
}
