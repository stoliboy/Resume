<%-- 
    Document   : thankyou
    Created on : Apr 30, 2023, 2:51:40 PM
    Author     : jessj
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html> 
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
         <title>HW5</title>
    </head>
    <body>
        <h1>Thank you for your order</h1>
       
        ${userHTML}
        ${cartHTML}
   
        &nbsp;
        &nbsp;
             <form action="cart" method="post"> 
                 <input type="hidden" name="action" value="return" >
             <input type="submit" value="return">
            </form>
        
    </body>
</html>
