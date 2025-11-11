<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order History</title>
</head>
<body>
<a href="mini-shop?action=dashboard">Back to DashBoard</a>
<c:if test="${not empty orders}">
    <table border="1">
        <thead>
        <tr>
            <th>Id</th>
            <th>User Id</th>
            <th>Product Id</th>
            <th>Quantity</th>
            <th>Name</th>
            <th>Price</th>
            <th>Total Price</th>
            <th>Ordered Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.id}</td>
                <td>${order.userId}</td>
                <td>${order.productId}</td>
                <td>${order.quantity}</td>
                <td>${order.productName}</td>
                <td>${order.productPrice}</td>
                <td>${order.totalPrice}</td>
                <td>${order.orderedDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${empty orders}">
    <p>No orders found from customers.</p>
</c:if>
</body>
</html>