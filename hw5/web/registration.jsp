<%-- 
    Document   : newjsp
    Created on : May 1, 2023, 9:15:57 AM
    Author     : jessj
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>HW4 Jess Payton</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<h1>New User Registration Page</h1>
    <body>
        <h1>Sign In</h1>
     
       <form action="user" method="post">
           <!<!-- need to populate email address -->
      <label class="box">Email:</label>
        <input type="email" name="email"  value="${email}" required ><br>
      
        <label class="box">Password</label>
        <input type="password" name="password" required><br>
      
        <label class="box">First Name</label><!-- comment -->
        <input type="text" name="firstName" required><br>
       
        <label class=""box">Last Name</label> 
        <input type="text" name="lastName" required><br>
   
  <input type="hidden" name="action" value="join">
  <input type="submit" value="Join Now">

 </form>
</body>
</html>