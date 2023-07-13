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

    
<h1>Book list</h1>

<form action="cart" method="post">  


${sqlResult}
 
</form>
</body>
</html>