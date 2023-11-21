<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.yash.model.Events, com.yash.dao.EventsDAO,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Event Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="/" class="navbar-brand"> Event Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Events</a></li>
			</ul>
		</nav>
	</header>
	
	<%
		int id = Integer.parseInt(request.getParameter("id"));
		Events e = EventsDAO.selectEventsByEventId(id);	
		
	%>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
					<form action="update" method="post">
			
				<caption>
					<h2>Edit Event</h2>
					
				</caption>

					<input type="hidden" name="id" value=<%=id %> />

				<fieldset class="form-group">
					<label>Event Name</label> 
					<input type="text"
						value=<%=e.getEvent_name()%> class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Event Date</label> 
					<input type="text"
						value=<%=e.getEvent_date()%> class="form-control"
						name="date">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Event Start Time</label> 
					<input type="text"
						value=<%=e.getStart_time()%> class="form-control"
						name="start_time">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Event End Time</label> 
					<input type="text"
						value=<%=e.getEnd_time() %> class="form-control"
						name="end_time">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Event Description</label> 
					<input type="text"
						value=<%=e.getDescription()%> class="form-control"
						name="description">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>