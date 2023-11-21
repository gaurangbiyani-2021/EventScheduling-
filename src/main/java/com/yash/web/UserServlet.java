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
import javax.servlet.http.HttpSession;

import org.omg.CORBA.ORBPackage.InvalidName;

import com.yash.dao.UserDAO;
import com.yash.model.*;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	
    public UserServlet() {
        this.userDAO = new UserDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request,response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		switch(action) { 	
		
		case "/reg":
				try {
					insertUser(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		
		case "/login":
				try {
					validateUser(request,response);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				break;
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			try {
				insertUser(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deleteUser(request, response);
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
				updateUser(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			try {
				listUser(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request,response);
	}
	
	public void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException{
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int contact = Integer.parseInt(request.getParameter("contact"));
		String description = request.getParameter("description");
		String designation = request.getParameter("designation");
		
		if(designation.equals("admin")) {
			response.sendRedirect("send_email.jsp");
		}else {
			User newUser = new User(name,email,password, contact,description,designation);
			userDAO.insertUser(newUser);
			response.sendRedirect("login.jsp");
		}
		
	}
	
	public static void validateUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        User newUser = new User(email,password);
        
        try {
            if (UserDAO.validate(newUser)) {
            	Cookie ck = new Cookie("User", UserDAO.validName(newUser));
            	Cookie ck1 = new Cookie("ID", Integer.toString(UserDAO.validId(newUser)));
            	Cookie ck2 = new Cookie("DESIGNATION", UserDAO.validDesignation(newUser));
            	response.addCookie(ck);
            	response.addCookie(ck1);
            	response.addCookie(ck2);
            	
            	
            	if(ck2.getValue().equals("admin")) {
            		response.sendRedirect("AdminDashboard.jsp");
            	}else {
            		 response.sendRedirect("event-list.jsp");
            	}
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("=>>>>>>>>>>>>>>>>>>"+id);
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		int contactno = Integer.parseInt(request.getParameter("contact"));
		String description = request.getParameter("description");
		String designation = request.getParameter("designation");
		User user = new User(id,name, email, contactno,description,designation);
		userDAO.updateUser(user);
		response.sendRedirect("list");
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<User> listUser = userDAO.selectAllUsers();
		request.setAttribute("listuser", listUser);
		System.out.println("list of user=>>>>>>>>>>>" +listUser.toString());
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);	
	}
	
	
}
	
