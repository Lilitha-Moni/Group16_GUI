<%--@elvariable id="admin" type="za.ac.cput.entity.Admin"--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Admin</h1>
<h3>View details</h3>
<br>
<p>Admin ID: <span>${admin.ID}</span></p>
<p>Admin: <span><a href="${pageContext.request.contextPath}/admin/${admin.ID}">${admin.firstName} ${admin.lastName}</a></span></p>
<p>Type: <span>${admin.type}</span></p>
<p>Email: <span>${admin.expireDate}</span></p>
<a href="${pageContext.request.contextPath}/admin/update/${admin.id}">Update</a>
<a href="${pageContext.request.contextPath}/admin/delete/${admin.id}" onclick="return confirm('Are you sure you want to remove admin ${admin.firstName} ${admin.lastName}\'?');">Delete</a>