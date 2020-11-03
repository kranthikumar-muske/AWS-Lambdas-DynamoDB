package com.family.tree.dynamoDB.DAO;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import com.family.tree.dynamoDB.guice.DynamoDBModule;
import com.family.tree.dynamoDB.table.mapping.Address;
import com.family.tree.dynamoDB.table.mapping.FamilyTreeTablePOJO;
import com.family.tree.dynamoDB.util.GenerateRandomUUID;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class PutDAOTest {

	private PutDAO putDAO;
	private CreateTableDAO createTableDAO;
	private GenerateRandomUUID generateRandomUUID;

	
	@Before
	public void begin() {
		final Injector injector = Guice.createInjector(new DynamoDBModule());
		this.putDAO = injector.getInstance(PutDAO.class);
		this.createTableDAO = injector.getInstance(CreateTableDAO.class);
		this.generateRandomUUID = injector.getInstance(GenerateRandomUUID.class);
		
	}
	
	@Test
	public void testCreateUserProfile() {
		
		createTableDAO.createTable(FamilyTreeTablePOJO.class);
		//	Create father		
        FamilyTreeTablePOJO father = new FamilyTreeTablePOJO();
        father.setUserId("user_"+"123");
        father.setRelationship("myself");
        father.setFirstName("Rajalingaiah");
        father.setLastName("Muske");
        father.setBirthPlace("HMPT");
        father.setDob("01011960");
        father.setAge("60");
        father.setGender("Male");
        father.setPhoneNumber("9866622999");
        father.setStatus(null);//by deafult living if not mentioned in front end
        Address fatheraddress = new Address();
        fatheraddress.setCity("Ramagundam");
        fatheraddress.setHouseNumber("5");
        fatheraddress.setPinCode(505209);
        fatheraddress.setStreetName("Rajiv swagruha");
        fatheraddress.setState("Telangana");
        father.setAddress(fatheraddress);
		
		putDAO.createUserProfile(father);
		
		//create mother
        FamilyTreeTablePOJO mother = new FamilyTreeTablePOJO();
        mother.setUserId("user_"+"456");
        mother.setRelationship("myself");
        mother.setFirstName("Laxmi");
        mother.setLastName("Muske");
        mother.setBirthPlace("BLMPL");
        mother.setDob("03061970");
        mother.setAge("55");
        mother.setGender("FeMale");
        mother.setPhoneNumber("9866622902");
        mother.setStatus(null);//by deafult living if not mentioned in front end
        Address motheraddress = new Address();
        motheraddress.setCity("Ramagundam");
        motheraddress.setHouseNumber("5");
        motheraddress.setPinCode(66223);
        motheraddress.setStreetName("rajiv swagruha");
        motheraddress.setState("Telangana");
        mother.setAddress(motheraddress);
        
        putDAO.createUserProfile(mother);
        
        //spouse row for father
        FamilyTreeTablePOJO fatherspouse = new FamilyTreeTablePOJO();
        fatherspouse.setUserId("user_"+"123");
        fatherspouse.setRelationship("spouse");
        fatherspouse.setSpousekey(mother.getUserId());
        
        putDAO.createUserProfile(fatherspouse);
        
        //spouse row for mother
        FamilyTreeTablePOJO motherspouse = new FamilyTreeTablePOJO();
        motherspouse.setUserId("user_"+"456");
        motherspouse.setRelationship("spouse");
        motherspouse.setSpousekey(father.getUserId());
        
        putDAO.createUserProfile(motherspouse);
        
        
        
        
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        //create child_1 satyam
        
        FamilyTreeTablePOJO son = new FamilyTreeTablePOJO();
        son.setUserId("user_"+"789");
        son.setRelationship("myself");
        son.setFirstName("Satyam");
        son.setLastName("Muske");
        son.setBirthPlace("GDK");
        son.setDob("02101985");
        son.setAge("35");
        son.setGender("Male");
        son.setPhoneNumber("986644552");
        son.setStatus(null);//by deafult living if not mentioned in front end
        Address sonaddress = new Address();
        sonaddress.setCity("Pune");
        sonaddress.setHouseNumber("55");
        sonaddress.setPinCode(505209);
        sonaddress.setStreetName("greens apts");
        sonaddress.setState("Maharastra");
        son.setAddress(sonaddress);
		
		putDAO.createUserProfile(son);
        
        //create child_1 satyam spouse maheswari
        
        FamilyTreeTablePOJO sonSpouse = new FamilyTreeTablePOJO();
        sonSpouse.setUserId("user_"+"1011");
        sonSpouse.setRelationship("myself");
        sonSpouse.setFirstName("Maheswari");
        sonSpouse.setLastName("Muske");
        sonSpouse.setBirthPlace("Ellandhu");
        sonSpouse.setDob("06101987");
        sonSpouse.setAge("32");
        sonSpouse.setGender("FeMale");
        sonSpouse.setPhoneNumber("98669992");
        sonSpouse.setStatus(null);//by deafult living if not mentioned in front end
        Address sonSpouseaddress = new Address();
        sonSpouseaddress.setCity("Pune");
        sonSpouseaddress.setHouseNumber("55");
        sonSpouseaddress.setPinCode(505209);
        sonSpouseaddress.setStreetName("greens apts");
        sonSpouseaddress.setState("Maharastra");
        sonSpouse.setAddress(sonSpouseaddress);
		
		putDAO.createUserProfile(sonSpouse);
        
        //create spouse rows for satyam and maheswari
		
        //spouse row for satyam
        FamilyTreeTablePOJO satyamspouse = new FamilyTreeTablePOJO();
        satyamspouse.setUserId("user_"+"789");
        satyamspouse.setRelationship("spouse");
        satyamspouse.setSpousekey(sonSpouse.getUserId());
        
        putDAO.createUserProfile(satyamspouse);
        
        //spouse row for maheswari
        FamilyTreeTablePOJO maheswarispouse = new FamilyTreeTablePOJO();
        maheswarispouse.setUserId("user_"+"1011");
        maheswarispouse.setRelationship("spouse");
        maheswarispouse.setSpousekey(son.getUserId());
		
        putDAO.createUserProfile(maheswarispouse);
        
        
        
        
        
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        
		//create apple profile
        FamilyTreeTablePOJO apple = new FamilyTreeTablePOJO();
        apple.setUserId("user_"+"1213");
        apple.setRelationship("myself");
        apple.setFirstName("Tanvi");
        apple.setLastName("Muske");
        apple.setBirthPlace("Pune");
        apple.setDob("01052014");
        apple.setAge("5");
        apple.setGender("FeMale");
        apple.setPhoneNumber("999999999");
        apple.setStatus(null);//by deafult living if not mentioned in front end
        Address appleaddress = new Address();
        appleaddress.setCity("Pune");
        appleaddress.setHouseNumber("55");
        appleaddress.setPinCode(505209);
        appleaddress.setStreetName("greens apts");
        appleaddress.setState("Maharastra");
        apple.setAddress(appleaddress);
		
		putDAO.createUserProfile(apple);
        
		//create apple child rows for parents
        //child row for satyam
        FamilyTreeTablePOJO satyamchild = new FamilyTreeTablePOJO();
        satyamchild.setUserId("user_"+"789");
        satyamchild.setRelationship("child_1213");
        satyamchild.setChildrenkey(apple.getUserId());
        
        putDAO.createUserProfile(satyamchild);
        
        //child row for maheswari
        FamilyTreeTablePOJO maheswarichild = new FamilyTreeTablePOJO();
        maheswarichild.setUserId("user_"+"1011");
        maheswarichild.setRelationship("child_1213");
        maheswarichild.setChildrenkey(apple.getUserId());
		
        putDAO.createUserProfile(maheswarichild);
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        //create satyam child rows for father and mother
        
        //for father
        FamilyTreeTablePOJO fatherchild = new FamilyTreeTablePOJO();
        fatherchild.setUserId("user_"+"123");
        fatherchild.setRelationship("child_789");
        fatherchild.setChildrenkey(son.getUserId());
        
        putDAO.createUserProfile(fatherchild);
        
        //for mother
        FamilyTreeTablePOJO motherchild = new FamilyTreeTablePOJO();
        motherchild.setUserId("user_"+"456");
        motherchild.setRelationship("child_789");
        motherchild.setChildrenkey(son.getUserId());
        
        putDAO.createUserProfile(motherchild);
	}
	
	
	

	
//	/**
//	 * TODO
//	 * 
//	 * 1. write a new row with new generated id user_id and with all details
//	 * 2. new row with existing parent_id (#user_id) sort key as children_autogeneratedid from step1 + childrenKey = #user<auto id from step1>
//	 */
//	void addChild();//pk = #user_<id> //sk = begins_with #children
//	
//	/**
//	 * TODO - reverse write Spike
//	 * 
//	  * 1. write a new row with new generated id user_id and with all details
//	 * 2. new row with existing child_id (#user_id) sort key as children_autogeneratedid from step1 + childrenKey = #user<auto id from step1>
//	 */
//	void addParent();// gsi relationship index //pk= #relationship_<id>
//	
//	/**
//	 * TODO
//	 * 
//	 * grab user_<id> and update existing row with spuse details.
//	 */
//	void addSpouse();// gsi children index // pk = #user_<id>
//	
}
