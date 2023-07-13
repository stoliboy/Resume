<%-- 
    Document   : signin
    Created on : Apr 29, 2023, 7:36:39 PM
    Author     : jessj
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Sign in Page</title>
    </head>
    <body>
        <h1>Sign In</h1>       
        <form action="user" method="post">
            <input type="hidden" name="action" value="submit"> 
        
            <label class="box">Email:</label>
            <input type="email" name="email" value="${email}" required><br>
            
            <label class="box">Password</label>
            <input type="password" name="password" value="${password}" required><br>        
            ${sqlResult}
           
            
            <input type="hidden" name="action" value="submit">
            <input type="submit" value="Submit">
            
        </form>
    </body>
</html>
