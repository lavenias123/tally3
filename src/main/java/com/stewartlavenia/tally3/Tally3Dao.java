package com.stewartlavenia.tally3;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.stewartlavenia.tally3.entity.Users;

@Repository
public class Tally3Dao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// genertate constructor using fields
	public Tally3Dao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// the entity list to return
	public List<Users> list() {
		String sql = "Select * from Users";
		
		List<Users> listUsers = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Users.class));
		
		return listUsers;
	}
	
	// the customer list to return
	public List<Users> customerList() {
		String sql = "Select * from Users "
				+ "join user_goals using(user_id);";
		
		List<Users> listCustomers = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Users.class));
		
		return listCustomers;
	}
	
	
	// insert a new row into DB
	public void save(Users users) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("users").usingColumns("first_name", "last_name", "email", "phone");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(users);
		insertActor.execute(param);
	}
	
	// get a specific row from DB
	public Users get(int user_id) {
		String sql = "Select * from users where user_id = ?";
		Users user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Users.class), user_id);
		
		return user;
		
	}
	
	// create or update DB
	public void update(Users users) {
		String sql = "Update users SET first_name=:firstName, last_name=:lastName, email=:email, phone=:phone WHERE user_id =:user_id";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(users);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.update(sql, param);
	}
	
	// delete a user

	public void delete(int user_id) {
		
		String sql = "delete from users Where user_id = ?";
		
		jdbcTemplate.update(sql, user_id);
	}
	
	// search test tutorial
//	public List<Users> acquireFNorLN(String first_name, String last_name) {
//		String sql= "Select * from users Where first_name = ?, "
//				+ " OR last_name = ?";
//		List<Users> user = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Users.class), first_name, last_name);
//				 return user;
//	}
	/*
	// not being used
	public List<Users> acquireFNorLN(String first_name, String last_name) {
		try {
			String sql= "Select * from users Where first_name = ?" 
					+ " OR last_name = ?";
			List<Users> user = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Users.class), first_name, last_name);
					 return user;
					 
			}catch (EmptyResultDataAccessException e){
				System.out.println("Expected at least one row returned but zero results found.");
				return null;
			}catch (IncorrectResultSizeDataAccessException e){
				System.out.println("The result wasn't the expected size.");
				return null;
			}
	}
*/
	public List<Users> searchForUser(String first_name, String last_name) {	
		
		String sql = "Select * from users where first_name = ? or last_name = ?";
		List<Users> listResults = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Users.class), first_name, last_name);
		//System.out.println("From the DAO " + listResults);
		//System.out.println("From the DAO This is the first name " + first_name);
		
		if (listResults.isEmpty()) {
			System.out.println("From the DAO: Sorry name not found");
			return null;
		} else {
			return listResults; 
		
		} 
		
		
	} // searchForUser end
	
	// search for first_name
	
public List<Users> searchByFirstName(String first_name) {	
		
		String sql = "Select * from users where first_name = ?";
		List<Users> firstNameListResults = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Users.class), first_name);
		System.out.println("From the DAO " + firstNameListResults);
		System.out.println("From the DAO This is the first name " + first_name);
		
		if (firstNameListResults.isEmpty()) {
			System.out.println("From the DAO: Sorry first name: " + first_name + " was not found");
			return null;
		} else {
			return firstNameListResults; 
		
		} 
		
		
	} // searchForFirstName end

// search for last_name

public List<Users> searchByLastName(String last_name) {	
	
	String sql = "Select * from users where last_name = ?";
	List<Users> lastNameListResults = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Users.class), last_name);
	System.out.println("From the DAO " + lastNameListResults);
	System.out.println("From the DAO This is the last name " + last_name);
	
	if (lastNameListResults.isEmpty()) {
		System.out.println("From the DAO: Sorry last name: " + last_name + " was not found");
		return null;
	} else {
		return lastNameListResults; 
	
	} 
	
	
} // searchForLastName end

}	// Tally3Dao end
	
	
/*
	// search for a specific user in DB
//		public Users searchForUser(String first_name, String last_name, String email, String phone) {
	public String List<Users> searchForUser(String first_name) {
//			String sql = "Select * from users where user_id = ? "
//			String sql = "Select * from users where first_name = ?, "
			String sql = "Select * from users where last_name = ?";
//					+ "And first_name = ?, "
//					+ " OR last_name = ?, "
//					+ " OR email = ?, "
//					+ " OR phone = ?";
//			Users users = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Users.class), first_name, last_name, email, phone);
			Users users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Users.class), first_name);
			System.out.println(users);
			return users;
			
		}
// 10-1 I'm adding try/catch block
	public List<Users> searchForUser(String first_name, String last_name) {
		String sql = "Select * from users where first_name = ? or last_name = ?";
		List<Users> listResults = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Users.class), first_name, last_name);
		System.out.println("From the DAO " + listResults);
		System.out.println("This is the first name " + first_name);
		return listResults; // changed from users 7/30 how can I return nada!
	}

	
}
*/
