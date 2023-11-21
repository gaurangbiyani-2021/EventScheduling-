<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <!DOCTYPE html
 
PUBLIC
 
"-//W3C//DTD HTML 4.01 Transitional//EN"
 
"http://www.w3.org/TR/html4/loose.dtd">


    <html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

        <title>Registration Form</title>
        <style>
            body {
              font-family: sans-serif;
              background-color: #f2f2f2;
              transition: background-color 0.5s ease-in-out;
            }
            
            body:hover {
              background-color: #eee;
            }
            
            h1 {
              text-align: center;
              margin-bottom: 20px;
            }
            
            h1:hover {
              color: white;
            }
            
            form {
              width: 500px;
              margin: 0 auto;
              padding: 20px;
              border: 1px solid #ccc;
              border-radius: 5px;
              background-color: #fff;
              animation-name: slideInUp;
              animation-duration: 1s;
              animation-fill-mode: both;
            }
            
            table {
              width: 100%;
            }
            
            td {
              padding: 5px;
            }
            
            input[type="text"],
            input[type="password"] {
              width: 100%;
              padding: 5px;
              border: 1px solid #ccc;
              border-radius: 3px;
            }
            
            input[type="text"]:focus,
            input[type="password"]:focus {
              border: 1px solid #007bff;
            }
            
            input[type="submit"] {
              background-color: #007bff;
              color: white;
              padding: 10px;
              border: none;
              border-radius: 5px;
              cursor: pointer;
            }
            
            input[type="submit"]:hover {
              background-color: #0069d9;
            }
            
            a {
              color: #007bff;
              text-decoration: none;
            }
            
            a:hover {
              color: #0069d9;
            }
            
            div {
              text-align: center;
              margin-top: 20px;
            }
            
            @keyframes bounce {
              0% {
                transform: translateY(0);
              }
              50% {
                transform: translateY(-20px);
              }
              100% {
                transform: translateY(0);
              }
            }
            
            @keyframes slideInUp {
              0% {
                transform: translateY(100%);
                opacity: 0;
              }
              100% {
                transform: translateY(0);
                opacity: 1;
              }
            }
        </style>
    </head>

    <body>
        <div align="center">
            <h1>Register Form</h1>
            
            <form action="reg" method="post">
                <table>
                    <tr>
                        <td>Name</td>
                        <td>
                            <input type="text" name="name" />
                        </td>
                    </tr>
                    <tr>
                    
                        <td>Email</td>
                        <td>
                            <input type="text" name="email" />
                        </td>
                    </tr>
                    
                    <tr>
                        <td>Password</td>
                        <td>
                            <input type="password" name="password" />
                        </td>
                    </tr>
                    
                    <tr>
                        <td>Contact</td>
                        <td>
                            <input type="text" name="contact" />
                        </td>
                    </tr>
                    
                    <tr>
                        <td>Description</td>
                        <td>
                            <input type="text" name="description" />
                        </td>
                    </tr>
                    
                     <tr>
    					<td>Designation</td>
    					<td>
        					<select name="designation">
            					<option value="user">User</option>
            					<option value="admin">Admin</option>
        					</select>
    					</td>
					</tr>
                     
                </table>
                <input type="submit" value="Submit" />
            </form>

            <div>
                <h4>Already registered ?</h4>
                <a href="login.jsp">Login here</a>
            </div>
    </body>

    </html>