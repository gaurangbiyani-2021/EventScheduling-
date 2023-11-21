package com.yash.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yash.dao.EventsDAO;
import com.yash.dao.UserDAO;
import com.yash.model.*;

@WebServlet("/events")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventsDAO eventDAO;
	
    public EventServlet() {
        this.eventDAO = new EventsDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request,response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		switch(action) { 	

		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			try {
				insertEvent(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deleteEvent(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "/edit":
			try {
				showEditForm(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				updateEvent(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/list":
			try {
				listEvent(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request,response);
	}
	
	public void insertEvent(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException{
		String name = request.getParameter("name");
		String event_date = request.getParameter("date");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String description = request.getParameter("description");
		Integer id=0;
		Cookie ck[] = request.getCookies();
		for(int i=0;i<ck.length;i++) {
			if(ck[i].getName().equals("ID")) {
				id=Integer.parseInt(ck[i].getValue());
			}
		}
		Events newEvent = new Events(id,name,event_date,start_time,end_time,description);
		eventDAO.insertEvent(newEvent);
		System.out.println("Uploded Successfully");
		response.sendRedirect("event-list.jsp");
		
	}
	
	public void deleteEvent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int id = Integer.parseInt(request.getParameter("id"));
		EventsDAO.deleteEvents(id);
		response.sendRedirect("event-list.jsp");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException{
		int id = Integer.parseInt(request.getParameter("event_id"));
		System.out.println("=>>>>>>>>>>>>>>>>>>"+id);
		List<Events> existingUser = EventsDAO.selectEvents(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("events", existingUser);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void updateEvent(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String event_date = request.getParameter("date");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String description = request.getParameter("description");
		Integer user_idInteger=0;
		Cookie ck[] = request.getCookies();
		for(int i=0;i<ck.length;i++) {
			if(ck[i].getName().equals("ID")) {
				user_idInteger=Integer.parseInt(ck[i].getValue());
			}
		}
		Events newEvent = new Events(id,user_idInteger,name,event_date,start_time,end_time,description);
		EventsDAO.updateEvents(newEvent);
		response.sendRedirect("event-list.jsp");
	}
	
	private void listEvent(HttpServletRequest request, HttpServletResponse response) throws Exception{
	     System.out.println("Inside the Event Controller");
//		List<Events> listEvent = EventsDAO.selectAllEvents();
//		request.setAttribute("listevent", listEvent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("event-list.jsp");
		dispatcher.forward(request, response);	
	}

}
	
