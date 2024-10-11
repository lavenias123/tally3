package com.stewartlavenia.tally3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.stewartlavenia.tally3.entity.Users;

import ch.qos.logback.classic.Logger;



class UsersDAOTest {

	private Tally3Dao dao;
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl("jdbc:mysql://localhost:3306/tallyCalCarbsDaily");
		datasource.setUsername("calCalc");
		datasource.setPassword("calCalc");
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	
		dao = new Tally3Dao(new JdbcTemplate(datasource));
	}
	

	@AfterEach
	void tearDownAfterClass() throws Exception {
	}

	@Test
	void testList() {
		List<Users> listUsers = dao.list();
		assertFalse(listUsers.isEmpty());

	}
	
	@Test
	void testSave() { // works
		Users users = new Users(36, "Jeff", "Simpson", "jSimpson@gmail.com", "000-010-8989");
		dao.save(users);
	}
	
	@Test
	void testGet() { // works
		int user_id = 26;
		Users user = dao.get(user_id);
//		System.out.println(user_id);
		assertNotNull(user);
	}
	
	@Test
	void testUpdate() { // doesn't work
		// with update I'm not creating a newUser am I?
		Users user = new Users();
		// I hadn't set a user_id I wanted to have updated
		// that was the solution adding a user_id to update!!!
		user.setUser_id(1);
		user.setFirstName("Sam");
		user.setLastName("Cooke");
		user.setEmail("sCooke@code.net");
		user.setPhone("213-773-4549");
		
		dao.update(user);
	}
	
	@Test
	void testDelete() { // works
		int user_id = 23;
		dao.delete(user_id);
	}
/*
	@Test
	public void returnResults() {
		List<Users> searchResults = dao.acquireFNorLN("Boogie", "?");
		for (Users user:searchResults) {
			System.out.println("UName: "+ first_name(),
					"LName: "+ last_Name());
			
		}
		assertEquals(2, searchResults.size());
	}
*/


	
}