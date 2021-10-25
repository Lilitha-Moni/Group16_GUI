<%--@elvariable id="membership" type="za.ac.cput.entity.Membership"--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Membership</h1>
<h3>View details</h3>
<br>
<p>Membership ID: <span>${membership.id}</span></p>
<p>Member: <span><a href="${pageContext.request.contextPath}/member/${membership.member.memberID}">${membership.member.firstName} ${membership.member.lastName}</a></span></p>
<p>Type: <span>${membership.type}</span></p>
<p>Total Fees: <span>${membership.totalFees}</span></p>
<p>Email: <span>${membership.expireDate}</span></p>
<a href="${pageContext.request.contextPath}/membership/update/${membership.id}">Update</a>
<a href="${pageContext.request.contextPath}/membership/delete/${membership.id}" onclick="return confirm('Are you sure you want to remove member ${membership.member.firstName} ${membership.member.lastName}\'s membership?');">Delete</a>