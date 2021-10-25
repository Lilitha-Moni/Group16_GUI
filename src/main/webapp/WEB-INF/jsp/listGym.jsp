<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Gym Management</title>
</head>
<body>
<h2>Gym Details </h2>

<br/><br/>

<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Manage</th>
        </tr>

        </thead>

        <c:forEach items="${listGym}" var="gym">
            <tbody>
            <tr>
                <td>${gym.gymID}</td>
                <td>${gym.gymName} </td>
                <td><a
                        href="/read/{id}=<c:out value='${gym.gymID}' />">View</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a href="/update=<c:out value='${gym.gymID}' />">Edit</a>

            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>

</body>
</html>