<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Medical Staff</h1>
<table>
    <thead>
        <tr>
            <th>Name</th>
            <th>Phone Number</th>
            <th>Email</th>
            <th>View</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
        <%--@elvariable id="allMedicalStaff" type="java.util.ArrayList"--%>
        <c:forEach items="${allMedicalStaff}" var="current">
            <tr>
                <td>${current.firstName} ${current.lastName}</td>
                <td>${current.phoneNumber}</td>
                <td>${current.email}</td>
                <td><a href="${pageContext.request.contextPath}/medicalstaff/${current.id}">view</a></td>
                <td><a href="${pageContext.request.contextPath}/medicalstaff/update/${current.id}">update</a></td>
                <td><a href="${pageContext.request.contextPath}/medicalstaff/delete/${current.id}" onclick="return confirm('Are you sure you want to remove this user as a member of the medical staff for this gym?');">delete</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/medicalstaff/create">Add new</a>