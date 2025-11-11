<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Category List</title>
</head>
<body>
<h1>Category List</h1>
<a href="mini-shop?action=category-create">Create New</a>
<c:if test="${not empty categories}">
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Created By Username</th>
            <th>Updated By</th>
            <th>Action</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <td>${category.createdByUsername}</td>
                <td>${category.updatedByUsername}</td>
                <td><a href="mini-shop?action=category-edit&id=${category.id}">Edit</a>
                |
                    <form action="mini-shop" method="post">
                        <input type="hidden" name="action" value="category-delete">
                        <input type="hidden" name="id" value="${category.id}">

                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${empty categories}">
    <p>No categories found.</p>
</c:if>
<c:if test="${not empty error}">
    <p style="color: red">${error}</p>
</c:if>
</body>
</html>