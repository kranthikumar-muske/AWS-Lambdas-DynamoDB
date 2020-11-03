package com.family.tree.dynamoDB.DAO;

import com.family.tree.dynamoDB.table.mapping.FamilyTreeTablePOJO;

public interface PutDAO {

	/**
	 * creates a new item on the table with Id and sort key as #profile_<id>
	 * @param familyTreeTablePOJO
	 */
	void createUserProfile(FamilyTreeTablePOJO familyTreeTablePOJO);

	/////note we should use transactions API here.
	
	/**
	 * TODO basically create 2 rows with this method.
	 * 
	 * 1. write a new row with new generated id user_id and with all details - call createuserprofile();
	 * 2. create a new row with existing father_id (#user_id) sort key as children_childId childrenKey = #user<childId>
	 * 2. create a new row with existing mother_id (#user_id) sort key as children_childId childrenKey = #user<childId>
	 */
	void createNewChildAndlinkToParents(FamilyTreeTablePOJO childDetails, FamilyTreeTablePOJO linkToFatherDetails, FamilyTreeTablePOJO linkToMotherDetails);//pk = #user_<id> //sk = begins_with #children
	
	/**
	 * TODO 
	 * 
	 * 1. created profiles for mom and dad
	 * 2. new row with father id and child id
	 * 3. new row with mother id and child id
	 */
	void createNewParentsAndLinkToChild(FamilyTreeTablePOJO fatherDetails, 
			FamilyTreeTablePOJO motherDetails,
			FamilyTreeTablePOJO linkChildToFather, 
			FamilyTreeTablePOJO linkChildToMother,
			FamilyTreeTablePOJO linkFatherToMother,
			FamilyTreeTablePOJO linkMotherToFather
			);// gsi relationship index //pk= #relationship_<id>
	
	/**
	 * TODO
	 * 
	 * 1. create a new row with wife createprofile() - spouse id
	 * 2. new row with existing user_id and sortkey = spouse and spouse_key = spouse_ID
	 * 3. new row with spouseID_id and sortkey = spouse and spouse_key = existing userID
	 */
	void createNewSpouseAndLinkToExistingPerson(FamilyTreeTablePOJO spouseDetails, FamilyTreeTablePOJO linkSpouseToExistingPerson, FamilyTreeTablePOJO linkExistingPersonToSpouse);// gsi children index // pk = #user_<id>
	
}
