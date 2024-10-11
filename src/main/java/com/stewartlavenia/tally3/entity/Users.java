package com.stewartlavenia.tally3.entity;

// import javax.validation.constraints;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;



public class Users {
	private int user_id;
	
	private String first_name;
	
	private String last_name;
	
	private String email;
	private String phone;
	
	// generate super class constructor
	public Users() {
	}
//	
	public Users(int user_id, String firstName, String lastName, String email, String phone) {
		super();
		this.user_id = user_id;
		this.first_name = firstName;
		this.last_name = lastName;
		this.email = email;
		this.phone = phone;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFirstName() {
		return first_name;
	}
	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}
	public String getLastName() {
		return last_name;
	}
	public void setLastName(String lastName) {
		this.last_name = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	// display details of entity
	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", firstName=" + first_name + ", lastName=" + last_name + ", email=" + email
				+ ", phone=" + phone + "]";
	}
	
}	
	

