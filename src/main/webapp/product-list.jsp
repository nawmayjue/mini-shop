<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Product List</title>
</head>
<body>
<h1>Product List</h1>

<c:if test="${userTypeName == 'Admin'}">
<a href="mini-shop?action=product-create">Create New</a>
<a href="mini-shop?action=shopping-cart">Shopping Cart</a>
</c:if>

<c:if test="${not empty products}">
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Image Url</th>
            <th>Created By</th>
            <th>Updated By</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.description}</td>
                <td>${product.imageUrl}</td>
                <td>${product.createdByUsername}</td>
                <td>${product.updatedByUsername}</td>
                <c:if test="${userTypeName == 'Admin'}">
                <td><a href="mini-shop?action=product-edit&id=${product.id}">Edit</a>
                |
                    <form action="mini-shop" method="post">
                        <input type="hidden" name="action" value="product-delete">
                        <input type="hidden" name="id" value="${product.id}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
                </c:if>
                <c:if test="${userTypeName == 'Customer'}">

                <td>
                <form action="mini-shop" method="post">
                    <input type="hidden" name="action" value="add-to-shopping-cart">
                    <input type="hidden" name="productId" value="${product.id}">
                     <input type="number" name="quantity" value="1" placeholder="quantity">
                    <button type="submit">Add To Cart</button>
                </form>
                </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<a href="mini-shop?action=dashboard">Back To Dashboard</a>
<c:if test="${empty products}">
    <p>No products found.</p>
</c:if>
</body>
</html>