<%-- 
    Document   : newjsp
    Created on : May 1, 2023, 9:15:57 AM
    Author     : jessj
--%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
    <h1> Your Cart </h1>

<table>
  <tr>
    
    <th>Cover</th>
    <th>Title</th>
    <th>Price</th>
    <th>Amount</th>
    <th>Quantity</th>
    <th></th>
  </tr>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="item" items="${cart.items}">
  <tr>
 
  <td>${item.product.cover}</td>
    <td>${item.product.description}</td>
    <td>${item.product.priceCurrencyFormat}</td>
    <td>${item.totalCurrencyFormat}</td>
       <td>
      <form action="" method="post">
        <input type="hidden" name="productCode" 
               value="<c:out value='${item.product.code}'/>" >
        <input type=text name="quantity" 
               value="<c:out value='${item.quantity}'/>" id="quantity">
        <input type="submit" value="Update">
      </form>
    </td>
    <td>
      <form action="" method="post">
        <input type="hidden" name="productCode" 
               value="<c:out value='${item.product.code}'/>">
        <input type="hidden" name="quantity" 
               value="0">
        <input type="submit" value="Remove Item">
      </form>
    </td>
  </tr>
</c:forEach>
</table>

<p><b>To change the quantity</b>, enter the new quantity 
      and click on the Update button.</p>
  
<form action="cart" method="post">
  <input type="hidden" name="action" value="shop">
  <input type="submit" value="Continue Shopping">
</form>

<form action="" method="post">
  <input type="hidden" name="action" value="checkout">
  <input type="submit" value="Checkout">
</form>

</body>
</html>