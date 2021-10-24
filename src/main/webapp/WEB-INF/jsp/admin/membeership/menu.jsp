<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Medical Staff</h1>
<table>
    <tr>
        <th>Name</th>
        <th>Phone Number</th>
        <th>Email</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <%--@elvariable id="allMedicalStaff" type="java.util.List"--%>
    <c:forEach items="${allMedicalStaff}" var="current">
        <tr>
            <td>${current.firstName} ${current.lastName}</td>
            <td>${current.phoneNumber}</td>
            <td>${current.email}</td>
            <td><a href="${pageContext.request.contextPath}/medicalstaff/update/${current.id}">update</a></td>
            <td><a href="${pageContext.request.contextPath}/medicalstaff/delete/${current.id}">delete</a></td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/medicalstaff/create">Add new</a>