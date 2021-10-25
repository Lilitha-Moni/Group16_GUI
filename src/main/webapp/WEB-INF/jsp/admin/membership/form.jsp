<%--@elvariable id="membership" type="za.ac.cput.entity.Membership"--%>
<%--@elvariable id="subheading" type="java.lang.String"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>Membership</h1>
<h3>${subheading}</h3>
<form method="POST">
    <input type="hidden" name="id" value="${empty membership ? membership.id : 1}">



    <label for="m_member">Member</label><br>
    <c:choose>
        <c:when test="${not empty membership}">
            <input type="hidden" name="member" value="${membership.member.memberID}">
            <input type="text" id="m_member" value="${membership.member.firstName} ${membership.member.lastName} [${membership.member.memberID}]" disabled="disabled">
        </c:when>
        <c:otherwise>
            <select name="member" id="m_member">
                <%--@elvariable id="memberships" type="java.util.List"--%>
                <c:forEach items="${memberships}" var="current">
                    <option value="${current}">${current.firstName} ${current.lastName} [${current.memberID}]</option>
                </c:forEach>
            </select>
        </c:otherwise>
    </c:choose><br>

    <label for="m_type">Last name</label><br>
    <input type="text" name="type" id="m_type" value="${membership.type}"><br>

    <label for="m_expireDate">Email</label><br>
    <input type="date" name="expireDate" id="m_expireDate" value=<fmt:formatDate pattern="yyyy-MM-dd" value="${membership.expireDate}"/>><br>

    <label for="m_totalFees">Total fees</label><br>
    <input type="text" name="totalFees" id="m_totalFees" value="${membership.totalFees}"><br>
    <br>
    <button type="submit">Submit</button>
</form>