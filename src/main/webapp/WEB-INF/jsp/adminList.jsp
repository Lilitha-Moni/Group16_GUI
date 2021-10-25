<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Admins</title>
    <link href="../resources/css/table_style.css" rel="stylesheet" type="text/css">

</head>
<body>

<h2>Admins</h2>

<a href="/admin/new">
    <button type="submit">Add new Admin</button>
</a>
<br/><br/>

<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>firstName</th>
            <th>lastName</th>
            <th>phoneNo</th>
            <th>emailAddress</th>
        </tr>

        </thead>

        <c:forEach items="${adminList}" var="admin">
            <tbody>
            <tr>
                <td>${admin.ID}</td>
                <td>${admin.firstName} ${admin.lastName}</td>
                <td> <a href="${pageContext.request.contextPath}/member/read/${admin.ID}">View</a>
                    <a
                            href="${pageContext.request.contextPath}/admin/update/${admin.ID}">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                            href="${pageContext.request.contextPath}/admin/delete/${admin.ID}">Delete</a></td>


            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>

</body>
</html>