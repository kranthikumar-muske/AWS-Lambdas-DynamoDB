package com.family.tree.lambda.functions.putOps;

import com.family.tree.dynamoDB.table.mapping.Address;
import com.family.tree.dynamoDB.table.mapping.FamilyTreeTablePOJO;
import com.family.tree.lambda.functions.putOps.requests.PutChildHttpRequest;
import com.family.tree.lambda.functions.putOps.requests.PutHttpRequest;
import com.family.tree.lambda.functions.putOps.requests.PutParentsHttpRequest;
import com.family.tree.lambda.functions.putOps.requests.PutSpouseHttpRequest;

public class FamilyTreeSetters {

	//sets user profile for creating new profile.
	public FamilyTreeTablePOJO setUserProfile(PutHttpRequest data, String userId){
        FamilyTreeTablePOJO familyTreeTablePOJO = new FamilyTreeTablePOJO();
        familyTreeTablePOJO.setUserId("user_"+userId);
        familyTreeTablePOJO.setRelationship("myself");
        familyTreeTablePOJO.setFirstName(data.getFirstName());
        familyTreeTablePOJO.setLastName(data.getLastName());
        familyTreeTablePOJO.setBirthPlace(data.getBirthPlace());
        familyTreeTablePOJO.setDob(data.getDob());
        familyTreeTablePOJO.setAge(data.getAge());
        familyTreeTablePOJO.setGender(data.getGender());
        familyTreeTablePOJO.setPhoneNumber(data.getPhoneNumber());
        familyTreeTablePOJO.setStatus(data.getStatus());//if value is null default value is set to "Living".
        Address address = new Address();
        address.setHouseNumber(data.getHouseNumber());
        address.setStreetName(data.getStreetName());
        address.setCity(data.getCity());
        address.setState(data.getState());
        address.setPinCode(data.getPinCode());
        familyTreeTablePOJO.setAddress(address);
        return familyTreeTablePOJO;
	}
	
	
	//sets spouse profile for creating new spouse profile.
	public FamilyTreeTablePOJO setSpouseProfile(PutSpouseHttpRequest data, String userId){
        FamilyTreeTablePOJO familyTreeTablePOJO = new FamilyTreeTablePOJO();
        familyTreeTablePOJO.setUserId("user_"+userId);
        familyTreeTablePOJO.setRelationship("myself");
        familyTreeTablePOJO.setFirstName(data.getFirstName());
        familyTreeTablePOJO.setLastName(data.getLastName());
        familyTreeTablePOJO.setBirthPlace(data.getBirthPlace());
        familyTreeTablePOJO.setDob(data.getDob());
        familyTreeTablePOJO.setAge(data.getAge());
        familyTreeTablePOJO.setGender(data.getGender());
        familyTreeTablePOJO.setPhoneNumber(data.getPhoneNumber());
        familyTreeTablePOJO.setStatus(data.getStatus());//if value is null default value is set to "Living".
        Address address = new Address();
        address.setHouseNumber(data.getHouseNumber());
        address.setStreetName(data.getStreetName());
        address.setCity(data.getCity());
        address.setState(data.getState());
        address.setPinCode(data.getPinCode());
        familyTreeTablePOJO.setAddress(address);
        return familyTreeTablePOJO;
	}
	
	//sets Father profile for creating new Father profile.
	public FamilyTreeTablePOJO setFatherProfile(PutParentsHttpRequest data, String fatherId){
        FamilyTreeTablePOJO familyTreeTablePOJO = new FamilyTreeTablePOJO();
        familyTreeTablePOJO.setUserId("user_"+fatherId);
        familyTreeTablePOJO.setRelationship("myself");
        familyTreeTablePOJO.setFirstName(data.getFatherFirstName());
        familyTreeTablePOJO.setLastName(data.getFatherLastName());
        familyTreeTablePOJO.setBirthPlace(data.getFatherBirthPlace());
        familyTreeTablePOJO.setDob(data.getFatherDob());
        familyTreeTablePOJO.setAge(data.getFatherAge());
        familyTreeTablePOJO.setGender(data.getFatherGender());
        familyTreeTablePOJO.setPhoneNumber(data.getFatherPhoneNumber());
        familyTreeTablePOJO.setStatus(data.getFatherStatus());//if value is null default value is set to "Living".
        Address address = new Address();
        address.setHouseNumber(data.getFatherHouseNumber());
        address.setStreetName(data.getFatherStreetName());
        address.setCity(data.getFatherCity());
        address.setState(data.getFatherState());
        address.setPinCode(data.getFatherPinCode());
        familyTreeTablePOJO.setAddress(address);
        return familyTreeTablePOJO;
	}
	
	//sets mother profile for creating new mother profile.
	public FamilyTreeTablePOJO setMotherProfile(PutParentsHttpRequest data, String motherId){
        FamilyTreeTablePOJO familyTreeTablePOJO = new FamilyTreeTablePOJO();
        familyTreeTablePOJO.setUserId("user_"+motherId);
        familyTreeTablePOJO.setRelationship("myself");
        familyTreeTablePOJO.setFirstName(data.getMotherFirstName());
        familyTreeTablePOJO.setLastName(data.getMotherLastName());
        familyTreeTablePOJO.setBirthPlace(data.getMotherBirthPlace());
        familyTreeTablePOJO.setDob(data.getMotherDob());
        familyTreeTablePOJO.setAge(data.getMotherAge());
        familyTreeTablePOJO.setGender(data.getMotherGender());
        familyTreeTablePOJO.setPhoneNumber(data.getMotherPhoneNumber());
        familyTreeTablePOJO.setStatus(data.getMotherStatus());//if value is null default value is set to "Living".
        Address address = new Address();
        address.setHouseNumber(data.getMotherHouseNumber());
        address.setStreetName(data.getMotherStreetName());
        address.setCity(data.getMotherCity());
        address.setState(data.getMotherState());
        address.setPinCode(data.getMotherPinCode());
        familyTreeTablePOJO.setAddress(address);
        return familyTreeTablePOJO;
	}
	
	//sets child profile for creating new child profile.
	public FamilyTreeTablePOJO setChildProfile(PutChildHttpRequest data, String childUserId){
        FamilyTreeTablePOJO familyTreeTablePOJO = new FamilyTreeTablePOJO();
        familyTreeTablePOJO.setUserId("user_"+childUserId);
        familyTreeTablePOJO.setRelationship("myself");
        familyTreeTablePOJO.setFirstName(data.getFirstName());
        familyTreeTablePOJO.setLastName(data.getLastName());
        familyTreeTablePOJO.setBirthPlace(data.getBirthPlace());
        familyTreeTablePOJO.setDob(data.getDob());
        familyTreeTablePOJO.setAge(data.getAge());
        familyTreeTablePOJO.setGender(data.getGender());
        familyTreeTablePOJO.setPhoneNumber(data.getPhoneNumber());
        familyTreeTablePOJO.setStatus(data.getStatus());//if value is null default value is set to "Living".
        Address address = new Address();
        address.setHouseNumber(data.getHouseNumber());
        address.setStreetName(data.getStreetName());
        address.setCity(data.getCity());
        address.setState(data.getState());
        address.setPinCode(data.getPinCode());
        familyTreeTablePOJO.setAddress(address);
        return familyTreeTablePOJO;
	}
	
	public FamilyTreeTablePOJO linkChildToFather(String childUserId, String fatherUserId){
        FamilyTreeTablePOJO familyTreeTablePOJO = new FamilyTreeTablePOJO();
        familyTreeTablePOJO.setUserId(fatherUserId);
        familyTreeTablePOJO.setRelationship("child_"+childUserId);
        familyTreeTablePOJO.setChildrenkey("user_"+childUserId);
        return familyTreeTablePOJO;
	}
	
	public FamilyTreeTablePOJO linkChildToMother(String childUserId, String motherUserId){
        FamilyTreeTablePOJO familyTreeTablePOJO = new FamilyTreeTablePOJO();
        familyTreeTablePOJO.setUserId(motherUserId);
        familyTreeTablePOJO.setRelationship("child_"+childUserId);
        familyTreeTablePOJO.setChildrenkey("user_"+childUserId);
        return familyTreeTablePOJO;
	}
	
	public FamilyTreeTablePOJO linkFatherToChild(String childUserId, String fatherUserId){
        FamilyTreeTablePOJO familyTreeTablePOJO = new FamilyTreeTablePOJO();
        familyTreeTablePOJO.setUserId("user_"+fatherUserId);
        familyTreeTablePOJO.setRelationship("child_"+childUserId);
        familyTreeTablePOJO.setChildrenkey(childUserId);
        return familyTreeTablePOJO;
	}
	
	public FamilyTreeTablePOJO linkMotherToChild(String childUserId, String motherUserId){
        FamilyTreeTablePOJO familyTreeTablePOJO = new FamilyTreeTablePOJO();
        familyTreeTablePOJO.setUserId("user_"+motherUserId);
        familyTreeTablePOJO.setRelationship("child_"+childUserId);
        familyTreeTablePOJO.setChildrenkey(childUserId);
        return familyTreeTablePOJO;
	}
	
	public FamilyTreeTablePOJO linkSpouseToExistingPerson(String spouseUserId, String existingUserId){
        FamilyTreeTablePOJO familyTreeTablePOJO = new FamilyTreeTablePOJO();
        familyTreeTablePOJO.setUserId(existingUserId);
        familyTreeTablePOJO.setRelationship("spouse");
        familyTreeTablePOJO.setSpousekey("user_"+spouseUserId);
        return familyTreeTablePOJO;
	}
	
	public FamilyTreeTablePOJO linkExistingPersonToSpouse(String spouseUserId, String existingUserId){
        FamilyTreeTablePOJO familyTreeTablePOJO = new FamilyTreeTablePOJO();
        familyTreeTablePOJO.setUserId("user_"+spouseUserId);
        familyTreeTablePOJO.setRelationship("spouse");
        familyTreeTablePOJO.setSpousekey(existingUserId);
        return familyTreeTablePOJO;
	}
}
