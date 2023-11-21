<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Schedule View</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="userdash.css">
</head>
<body>
<%@page import="com.yash.dao.EventsDAO,com.yash.model.*,java.util.*"%>
	<header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background: #272343 ;">
                    <div>
                        <a href="#" class="navbar-brand" style="color:white;">Trainer Schedule Management</a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="AdminDashboard.jsp" class="nav-link" style="position: relative; left: 1000px; color:white;">Dashboard</a></li>
                    </ul>
                </nav>
     </header>
     <br>
     <%
     	int id = Integer.parseInt(request.getParameter("id"));
     	String name = request.getParameter("id1");
     	List<Events> list = EventsDAO.selectEvents(id);
     	request.setAttribute("list", list);
     %>
     <div>
     	<div class="box3">
     	</div>
     	<div class="box4">
     		<h1 style="position: relative; left:10px;">Schedule of <%= name  %></h1>
     		<h2 id="listing">List</h2>
     		<table border="1" width="90%" id="table1">  
				<tr><th>Event Name</th><th>Event Date</th><th>Event Start Time</th><th>Event End Time </th><th>Event Description</th></tr>  
				<c:forEach items="${list}" var="u">  
				<tr><td>${u.getEvent_name()}</td><td>${u.getEvent_date()}</td><td>${u.getStart_time()}</td><td>${u.getEnd_time()}</td><td>${u.getDescription()}</td></tr>  
				</c:forEach>  
			</table>
     	</div>
     </div>
</body>
</html>