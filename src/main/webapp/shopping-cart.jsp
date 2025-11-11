<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
<h1>"${username}'s Shopping Cart"</h1>
<a href="mini-shop?action=dashboard">Back to DashBoard</a>
<c:if test="${not empty error}">
<p>${error}</p>
</c:if>
<c:if test="${not empty shoppingCarts}">
    <table border="1">
        <thead>
        <tr>
            <th>Product Id</th>
            <th>Quantity</th>
            <th>Name</th>
            <th>Price</th>
            <th>Total Price</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="shoppingCart" items="${shoppingCarts}">
            <tr>
                <td>${shoppingCart.productId}</td>
                <td>${shoppingCart.quantity}</td>
                <td>${shoppingCart.productName}</td>
                <td>${shoppingCart.productPrice}</td>
                <td>${shoppingCart.totalPrice}</td>
                <td>
                    <form action="mini-shop" method="post">
                        <input type="hidden" name="action" value="check-out">
                        <input type="hidden" name="productId" value="${shoppingCart.productId}">
                        <input type="hidden" name="id" value="${shoppingCart.id}">
                        <input type="hidden" name="originalQuantity" value="${shoppingCart.quantity}">
                        <input type="number" name="quantity" value="${shoppingCart.quantity}" placeholder="quantity">
                        <button type=submit>Check Out</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${empty shoppingCarts}">
    <p>No products found.</p>
</c:if>
</body>
</html>