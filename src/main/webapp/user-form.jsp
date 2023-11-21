<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${event != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${event == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
				
					<h2>
						<c:if test="${event != null}">Edit Event </c:if>
						<c:if test="${event == null}"> New Event</c:if>
					</h2>
					
				</caption>

				<c:if test="${event != null}">
					<input type="hidden" name="id" value="<c:out value='${event.event_id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Event Name</label> 
					<input type="text"
						value="<c:out value='${event.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Event Date</label> 
					<input type="text"
						value="<c:out value='${event.date}' />" class="form-control"
						name="date">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Event Start Time</label> 
					<input type="text"
						value="<c:out value='${event.start_time}' />" class="form-control"
						name="start_time">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Event End Time</label> 
					<input type="text"
						value="<c:out value='${event.end_time}' />" class="form-control"
						name="end_time">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Event Description</label> 
					<input type="text"
						value="<c:out value='${event.description}' />" class="form-control"
						name="description">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>