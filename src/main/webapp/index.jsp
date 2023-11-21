<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <title>Events Scheduling</title>
  <style>
  	body{
  		background-image:url('https://www.amitree.com/wp-content/uploads/2021/08/the-pros-and-cons-of-paper-to-do-lists.jpeg');
  	}
    .navbar {
      background-color: tomato;
      border-bottom: 1px solid #ccc;
      padding: 10px 20px;
    }
    .navbar-brand {
      font-size: 30px;
      font-weight: bold;
    }
    .navbar .navbar-nav {
      float: right;
    }
    .navbar-nav .b1{
      background-color: green;
      display: inline-block;
      margin-left: 10px;
      color:white;
      border-radius:20px;
    }
	 .navbar .navbar-nav .b2{
      background-color: green;
      display: inline-block;
      margin-left: 10px;
      color:white;
      border-radius:20px;
    }
    .navbar-nav a {
      color: #333;
      text-decoration: none;
      font-size:20px;
      
      
    }
    .navbar-nav a:hover {
      color: #000;
    }
  </style>
</head>
<body>
  <nav class="navbar">
    <h1 class="navbar-brand" href="/">Events Scheduling
      <ul class="navbar-nav">
      <button class="b1"><a href="register.jsp"><b style="color:white">Register</b></a></button>
      <button class="b2"><a href="login.jsp"><b style="color:white">Login</b></a></button> 
    </ul>
    </h1>
    
  </nav>

  <main>
    <h1 style="margin-left:10px">Welcome to Events Scheduling!</h1>
    <p style="margin-left:10px"><b>This is a website where you can manage all of your event scheduling needs.<br>You can create events, invite guests. You can also view a calendar of<br> all of your upcoming events.To get started, please register for an account<br> or log in if you already have one.</b></p>
  </main>
</body>
</html>