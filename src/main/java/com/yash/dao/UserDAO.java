package com.yash.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;

import com.yash.model.*;

public class UserDAO  extends HttpServlet{
	private static String jdbcURL = "jdbc:mysql://localhost:3306/trainer";
	private static String jdbcUsername = "abc";
	private static String jdbcPassword = "Abc";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO users"+" (name,email,password, contact,description,designation) VALUES " + "(?,?,?,?,?,?);";
	private static final String SELECT_USER_BY_ID = "select id,name,email,contact,description,designation from user where id=?";
	private static final String SELECT_ALL_USERS = "select * from users;";
	private static final String SELECT_ALL_USERS_BY_DESIGNATION = "select * from users where designation=?;";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name=?, email=?, contact=?, description=?, designation=? where id=?;";
	
	protected static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;	
	}
	
	//create or insert user 
	
	public void insertUser(User user) throws SQLException{
		
		try{
			Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
//			preparedStatement.setInt(1,user.getId());
			preparedStatement.setString(1,user.getName());
			preparedStatement.setString(2,user.getEmail());
			preparedStatement.setString(3,user.getPassword());
			preparedStatement.setInt(4,user.getcontact());
			preparedStatement.setString(5,user.getdescription());
			preparedStatement.setString(6,user.getDesignation());
			preparedStatement.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 public static boolean validate(User user) throws Exception{
	        boolean status = false;

	        try {
	        	
	        	
	        	Connection connection = getConnection();
	            	PreparedStatement preparedStatement = connection.prepareStatement("select * from users where email = ? and password = ? ");
	            preparedStatement.setString(1, user.getEmail());
	            preparedStatement.setString(2, user.getPassword());
	            ResultSet rs = preparedStatement.executeQuery();
	            status = rs.next();
	            
//	            Cookie  ck = new Cookie("userId",Integer.toString(user.getId()));
//	            response.addCookie(ck);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
			return status;
	    }
	 
	 //get user name after login
	 public static String validName(User u) {
		 String nameString="";
		 try {
			 Connection connection = getConnection();
         	PreparedStatement preparedStatement = connection.prepareStatement("select * from users where email = ? and password = ? ");
	         preparedStatement.setString(1, u.getEmail());
	         preparedStatement.setString(2, u.getPassword());
	         ResultSet rs = preparedStatement.executeQuery();
	         while(rs.next()) {
	        	 nameString=rs.getString("name");
	         }
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 return nameString;
	 }
	
	 public static Integer validId(User u) {
		 Integer id=0;
		 try {
			 Connection connection = getConnection();
         	PreparedStatement preparedStatement = connection.prepareStatement("select * from users where email = ? and password = ? ");
	         preparedStatement.setString(1, u.getEmail());
	         preparedStatement.setString(2, u.getPassword());
	         ResultSet rs = preparedStatement.executeQuery();
	         while(rs.next()) {
	        	 id=rs.getInt("id");
	         }
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 return id;
	 }
	 
	 //get user name after login
	 public static String validDesignation(User u) {
		 String nameString="";
		 try {
			 Connection connection = getConnection();
         	PreparedStatement preparedStatement = connection.prepareStatement("select * from users where email = ? and password = ? ");
	         preparedStatement.setString(1, u.getEmail());
	         preparedStatement.setString(2, u.getPassword());
	         ResultSet rs = preparedStatement.executeQuery();
	         while(rs.next()) {
	        	 nameString=rs.getString("designation");
	         }
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 return nameString;
	 }
	 
	//update user 
	
	public boolean updateUser(User user) throws SQLException{
		boolean rowUpdated;
		
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)){
			statement.setString(1,user.getName());
			statement.setString(2,user.getEmail());
			statement.setInt(3,user.getcontact());
			statement.setString(4,user.getdescription());
			statement.setInt(5,user.getId());
			statement.setString(6,user.getDesignation());
			
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	// select user by id 
	
	public User selectUser(int id) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				int contactno = rs.getInt("contact");
				String descriptionription = rs.getString("description");
				String designation = rs.getString("designation");
				
				user = new User(id,name, email, contactno,descriptionription,designation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	// select all user 
	
	public static List<User> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<User> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				int contactno = rs.getInt("contact");
				String description = rs.getString("description");
				String designation = rs.getString("designation");
				users.add(new User(id, name, email, contactno,description,designation));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	// select all user 
	
		public static List<User> selectAllUsersbyDesignation() {

			// using try-with-resources to avoid closing resources (boiler plate code)
			List<User> users = new ArrayList<>();
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_BY_DESIGNATION);) {
				// Step 3: Execute the query or update query
				preparedStatement.setString(1,"user");
				ResultSet rs = preparedStatement.executeQuery();
				
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					int contactno = rs.getInt("contact");
					String description = rs.getString("description");
					String designation = rs.getString("designation");
					users.add(new User(id, name, email, contactno,description,designation));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return users;
		}


	// delete user 
	
	public boolean deleteUser(int id) throws Exception{
		boolean rowDeleted = false;
		
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);){
			statement.setInt(1,id);
			rowDeleted = statement.executeUpdate() > 0;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return rowDeleted;
	}	
}

