<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h1>Welcome, ${username}</h1>
    <h1>User Type: ${userTypeName}</h1>
     <c:if test="${userTypeName == 'Admin'}">
     <a href="mini-shop?action=category-list">CategoryList</a>
     <a href="mini-shop?action=order-history-for-admin">Order History of Customers</a>
     </c:if>
    <a href="mini-shop?action=product-list">ProductList</a>
    <c:if test="${userTypeName == 'Customer'}">
    <a href="mini-shop?action=shopping-cart">Shopping Cart</a>
    <a href="mini-shop?action=order-history">Order History</a>
    </c:if>


</body>
</html>