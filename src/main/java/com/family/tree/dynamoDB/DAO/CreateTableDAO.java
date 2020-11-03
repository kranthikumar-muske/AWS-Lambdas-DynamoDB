package com.family.tree.dynamoDB.DAO;

public interface CreateTableDAO {
	
	/**
	 * creates a table in dynamoDB if doesn't exists.
	 * 
	 * @param pojoClass the pojo class which maps to the table.
	 */
	void createTable(Class<?> pojoClass);

}
