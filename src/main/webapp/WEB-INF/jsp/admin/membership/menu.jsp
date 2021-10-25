<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Membership</h1>
<table>
    <tr>
        <th>Member</th>
        <th>Type</th>
        <th>Total fees</th>
        <th>Expiry date</th>
        <th>View</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <%--@elvariable id="allMemberships" type="java.util.List"--%>
    <c:forEach items="${allMemberships}" var="current">
        <tr>
            <td>${current.member.firstName} ${current.member.lastName}</td>
            <td>${current.type}</td>
            <td>${current.totalFees}</td>
            <td>${current.expireDate}</td>
            <td><a href="${pageContext.request.contextPath}/membership/${current.id}">view</a></td>
            <td><a href="${pageContext.request.contextPath}/membership/update/${current.id}">update</a></td>
            <td><a href="${pageContext.request.contextPath}/membership/delete/${current.id}" onclick="return confirm('Are you sure you want to remove member ${current.member.firstName} ${current.member.lastName}\'s membership?');">delete</a></td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/membership/create">Add new</a>