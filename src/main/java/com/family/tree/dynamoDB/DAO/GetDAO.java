package com.family.tree.dynamoDB.DAO;

import java.util.List;
import com.family.tree.dynamoDB.table.mapping.ReplyPOJO;

public interface GetDAO {

	/**
	 * TODO
	 */
	List<ReplyPOJO> getChildrensByParentId(String parentUserId);//pk = #user_<id> //sk = begins_with #children
	
	
	/**
	 * TODO
	 */
	List<ReplyPOJO> getProfileById(String userId);// gsi relationship index //pk= #relationship_<id>
	
	
	/**
	 * TODO
	 */
	List<ReplyPOJO> getParentsInfoByChildId(String childUserId);// gsi children index // pk = #user_<id>
	

	/**
	 * TODO
	 */
	List<ReplyPOJO> getProfileByName(String firstName);// scan by first name
	
}
