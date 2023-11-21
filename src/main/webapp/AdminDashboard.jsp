<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Dashboard</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="userdash.css">
<script>history.forward();</script>
</head>
<body>
<%@page import="com.yash.dao.UserDAO,com.yash.model.*,java.util.*"%>
	<header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background: #272343 ;">
                    <div>
                        <a href="#" class="navbar-brand" style="color:white;">Trainer Schedule Management</a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="login.jsp" class="nav-link" style="position: relative; left: 1100px; color:white;">Log Out</a></li>
                    </ul>
                </nav>
     </header>
     <br>
     <%
     	Cookie ck[] = request.getCookies();
     	List<User> list = UserDAO.selectAllUsersbyDesignation();
     	request.setAttribute("list", list);
     	
     	String c="";
	  	for(int i=0;i<ck.length;i++){
	  		if(ck[i].getName().equals("User")){
	  			c = ck[i].getValue();
	  		}
	  	}
	  	
     %>
     <div>
     	<div class="box3">
     </div>
     <div class="box4">
     		<h1 style="position: relative; left:10px;">Welcome Manager - <%=c %></h1>
     		<h2 id="listing">Trainer's List</h2>
     		<table border="1" width="90%" id="table1">  
				<tr><th>Trainer Username</th><th>Trainer Email</th><th>View Schedule</th></tr>  
				<c:forEach items="${list}" var="u">  
				<tr><td>${u.getName()}</td><td>${u.getEmail()}</td>
				<td><a href="ViewforAdmin.jsp?id=${u.getId()}&id1=${u.getName()}">View Schedule</a></td></tr>
				</c:forEach>  
			</table>
     	</div>
     </div>
</body>
</html>