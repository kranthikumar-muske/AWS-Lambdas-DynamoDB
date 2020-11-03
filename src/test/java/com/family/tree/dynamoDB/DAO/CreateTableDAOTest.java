package com.family.tree.dynamoDB.DAO;

import org.junit.Before;
import org.junit.Test;

import com.family.tree.dynamoDB.guice.DynamoDBModule;
import com.family.tree.dynamoDB.table.mapping.FamilyTreeTablePOJO;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * test class for create table interface
 * 
 * @author km056366
 *
 */
public class CreateTableDAOTest {

	private CreateTableDAO createTableDAO;
	
	@Before
	public void begin() {
		final Injector injector = Guice.createInjector(new DynamoDBModule());
		this.createTableDAO = injector.getInstance(CreateTableDAO.class);
	}
	
	@Test
	public void testCreateTable() {
		createTableDAO.createTable(FamilyTreeTablePOJO.class);
	}
}
