package com.yash.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.events.EventException;

import com.yash.model.*;

public class EventsDAO{
	
	private static String jdbcURL = "jdbc:mysql://localhost:3306/trainerschedule";
	private static String jdbcEventsname = "root";
	private static String jdbcPassword = "Root";
	
	private static final String INSERT_EVENTS_SQL = "INSERT INTO events(event_name,event_date,start_time,end_time,description,user_id) VALUES (?,?,?,?,?,?);";
	private static final String SELECT_EVENT_BY_ID = "select event_id,event_name,event_date,start_time,end_time,description from events where user_id=?";
	private static final String SELECT_ALL_EVENTS = "select * from events";
	private static final String DELETE_EVENTS_SQL = "delete from events where event_id = ?;";
	private static final String UPDATE_EVENTS_SQL = "update events set event_name=?, event_date=?, start_time=?,end_time=?,description=? where event_id=?;";
	
	protected static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL,jdbcEventsname,jdbcPassword);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;	
	}
	
	//create or insert event 
	
	public void insertEvent(Events event) throws SQLException{
		
		try{
			Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENTS_SQL);
//			preparedStatement.setInt(1,user.getId());
			preparedStatement.setString(1,event.getEvent_name());
			preparedStatement.setString(2,event.getEvent_date());
			preparedStatement.setString(3,event.getStart_time());
			preparedStatement.setString(4,event.getEnd_time());
			preparedStatement.setString(5,event.getDescription());
			preparedStatement.setInt(6, event.getUser_id());
			preparedStatement.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//update event 
	
	public static boolean updateEvents(Events event) throws SQLException{
		boolean rowUpdated;
		
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EVENTS_SQL)){
			statement.setString(1,event.getEvent_name());
			statement.setString(2,event.getEvent_date());
			statement.setString(3,event.getStart_time());
			statement.setString(4,event.getEnd_time());
			statement.setString(5,event.getDescription());
			statement.setInt(6,event.getEvent_id());
			
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	// select user by id 
	
	public static List<Events> selectEvents(int user_id) {
		List<Events> event = new ArrayList<>();;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EVENT_BY_ID);) {
			preparedStatement.setInt(1, user_id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				Integer id = rs.getInt("event_id");
				String name = rs.getString("event_name");
				String date = rs.getString("event_date");
				String start_time = rs.getString("start_time");
				String end_time = rs.getString("end_time");
				String description = rs.getString("description");
				event.add(new Events(id,user_id ,name, date,start_time ,end_time,description));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}
	
	public static Events selectEventsByEventId(int event_id) {
		Events e = null;
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("select * from events where event_id=?;");) {
			preparedStatement.setInt(1, event_id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				Integer user_id = rs.getInt("user_id");
				String name = rs.getString("event_name");
				String date = rs.getString("event_date");
				String start_time = rs.getString("start_time");
				String end_time = rs.getString("end_time");
				String description = rs.getString("description");
				e = new Events(event_id,user_id, name, date, start_time, end_time, description);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return e;
	}
	// select all event 
	
	public static List<Events> selectAllEvents() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Events> events = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EVENTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("event_id");
				String name = rs.getString("event_name");
				String date = rs.getString("event_date");
				String start_time = rs.getString("start_time");
				String end_time = rs.getString("end_time");
				String description = rs.getString("description");
				Integer user_id = rs.getInt("user_id");
				events.add(new Events(id,user_id,name, date,start_time ,end_time,description));
//				System.out.println(id);
//				System.out.println(name);
//				System.out.println(date);
//				System.out.println(start_time);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events;
	}

	// delete event 
	
	public static int deleteEvents(int event_id) throws Exception{
		int rowDeleted = 0;
		
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_EVENTS_SQL);){
			statement.setInt(1,event_id);
			rowDeleted = statement.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return rowDeleted;
	}
}
