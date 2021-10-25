<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Admin</h1>
<table>
    <tr>
        <th>Admin</th>
        <th>Phone Number</th>
        <th>Email</th>
        <th>View</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <%--@elvariable id="AdminList" type="java.util.List"--%>
    <c:forEach items="${allAdmins}" var="current">
        <tr>
            <td>${current.admin.fisrtName} ${current.admin.lastName}</td>
            <td>${current.phoneNumber}</td>
            <td>${current.email}</td>
            <td>${current.expireDate}</td>
            <td><a href="${pageContext.request.contextPath}/admin/${current.id}">view</a></td>
            <td><a href="${pageContext.request.contextPath}/admin/update/${current.id}">update</a></td>
            <td><a href="${pageContext.request.contextPath}/admin/delete/${current.id}" onclick="return confirm('Are you sure you want to remove admin ${current.admin.firstName} ${current.admin.lastName}\'?');">delete</a></td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/admin/create">Add new</a>