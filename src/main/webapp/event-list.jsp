
<%@page import="com.yash.dao.EventsDAO"%>
<%@page import="org.eclipse.jdt.internal.compiler.parser.RecoveredRequiresStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.yash.web.EventServlet, java.util.* , com.yash.model.Events"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script>history.forward();</script>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="event-list.jsp"navbar-brand"> Event Management App </a>
			</div>
			
		</nav>
	</header>
	<br>

	<%
		Cookie ck[] = request.getCookies();
		Integer id=0;
		for(int i=0;i<ck.length;i++){
			if(ck[i].getName().equals("ID")){
				id=Integer.parseInt(ck[i].getValue());
			}
		}
		List<Events> listEvent = EventsDAO.selectEvents(id);
		request.setAttribute("listevent", listEvent);
	%>
	
	
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Events</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Event</a>
				<a href="generatePDF"  class="btn btn-success">Download PDF </a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Event Id</th>
						<th>Event Name</th>
						<th>Event Date</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
				
            	
					
					<c:forEach var="event" items="${listevent}">

						<tr>
							<td><c:out value="${event.event_id}" /></td>
							<td><c:out value="${event.event_name}" /></td>
							<td><c:out value="${event.event_date}" /></td>
							<td><c:out value="${event.start_time}" /></td>
							<td><c:out value="${event.end_time}" /></td>
							<td><c:out value="${event.description}" /></td>
							<td><a href="edit.jsp?id=<c:out value='${event.event_id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="delete?id=<c:out value='${event.event_id}' />">Delete</a>
							</td>
						</tr>
					</c:forEach>
				
				</tbody>

			</table>
		</div>
		
	</div>
</body>
</html>