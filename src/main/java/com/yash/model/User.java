package com.yash.model;

public class User{
	
	private int id;
	private String name;
	private String email;
	private String password;
	private int contact;
	private String description;
	private String designation;

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String name, String email, String password, int contact, String description, String designation) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.description = description;
		this.designation = designation;
	}
	public User(String name, String email, String password, int contact, String description, String designation ) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.description = description;
		this.designation = designation;
	}
	public User(int id, String name, String email, int contact, String description, String designation) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.description = description;
		this.designation = designation;
	}
	
	public User( String name, String email, int contact, String description, String designation) {
		super();
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.description = description;
		this.designation = designation;
	}
	
	public User(  String email , String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getcontact() {
		return contact;
	}
	public void setcontact(int contact) {
		this.contact = contact;
	}
	public String getdescription() {
		return description;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	
}


//
//CREATE TABLE User (
//	    id int NOT NULL AUTO_INCREMENT,
//	    Name varchar(255),
//	    Eamil varchar(255),
//	    Password varchar(255),
//	    Contact int,
//	    description varchar(255),
//	 
//	);
