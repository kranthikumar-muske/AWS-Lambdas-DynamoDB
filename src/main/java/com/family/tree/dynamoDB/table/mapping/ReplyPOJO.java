package com.family.tree.dynamoDB.table.mapping;

public class ReplyPOJO {

	private FamilyTreeTablePOJO myself;
	private FamilyTreeTablePOJO spouse;
	
	public FamilyTreeTablePOJO getMyself() {
		return myself;
	}
	public void setMyself(FamilyTreeTablePOJO myself) {
		this.myself = myself;
	}
	public FamilyTreeTablePOJO getSpouse() {
		return spouse;
	}
	public void setSpouse(FamilyTreeTablePOJO spouse) {
		this.spouse = spouse;
	}
	

}
