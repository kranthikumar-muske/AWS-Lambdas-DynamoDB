package com.family.tree.lambda.functions.putOps;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.family.tree.dynamoDB.DAO.PutDAO;
import com.family.tree.dynamoDB.guice.DynamoDBModule;
import com.family.tree.dynamoDB.util.GenerateRandomUUID;
import com.family.tree.lambda.functions.putOps.replys.PutHttpResponse;
import com.family.tree.lambda.functions.putOps.requests.PutParentsHttpRequest;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class AddParentsLambda  extends FamilyTreeSetters implements RequestHandler<PutParentsHttpRequest, PutHttpResponse> {
	private PutDAO putDAO;
	private GenerateRandomUUID generateRandomUUID;

	
	public AddParentsLambda() {
		final Injector injector = Guice.createInjector(new DynamoDBModule());
		this.putDAO = injector.getInstance(PutDAO.class);
		this.generateRandomUUID = injector.getInstance(GenerateRandomUUID.class);
	}
	
    @Override
    public PutHttpResponse handleRequest(PutParentsHttpRequest request, Context context) {
        context.getLogger().log("Input: " + request);

        String fatherUserId = generateRandomUUID.buildRandomUUID();
        String motherUserId = generateRandomUUID.buildRandomUUID();
        String childId = request.getChildUserId();
        
        putDAO.createNewParentsAndLinkToChild(setFatherProfile(request, fatherUserId),
        		setMotherProfile(request, motherUserId), 
        		linkFatherToChild(childId, fatherUserId),
        		linkMotherToChild(childId, motherUserId),
        		linkSpouseToExistingPerson(motherUserId, "user_"+fatherUserId),
        		linkExistingPersonToSpouse(motherUserId, "user_"+fatherUserId));
        
        return new PutHttpResponse();
    }
	
}