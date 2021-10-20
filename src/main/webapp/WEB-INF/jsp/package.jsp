<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Packages</title>
    <link href="../resources/css/table_style.css" rel="stylesheet" type="text/css">
</head>

<body>
<h2> Available Packages</h2>
<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th>Package ID</th>
            <th>Package Name</th>
            <th>Membership ID</th>
            <th>Hours Per Week</th>
            <th>Price</th>
        </tr>

        </thead>
        <c:forEach items="${package}" var="package">
            <tbody>
            <tr>
                <td>${package.packageId}</td>
                <td>${package.PackageName} ${package.MembershipId}${package.HoursPerWeek}${package.Price}</td>
                <td><a
                        href="/read/{id}=<c:out value='${package.packageId}' />">View</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                            href="/update=<c:out value='${package.packageId}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                            href="/delete/{id}=<c:out value='${package.packageId}' />">Delete</a></td>

            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>

</body>
</html>