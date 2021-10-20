<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Payment</title>
    <link href="../resources/css/table_style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>${payment.paymentAmount} ${payment.paymentDate} Information</h2>
<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>paymentAmount</th>
            <th>paymentDate</th>
        </tr>

        </thead>

        <tbody>
        <tr>
            <td>${payment.paymentID}</td>
            <td>${payment.paymentAmount}</td>
            <td>${payment.paymentDate}</td>
                <a href="/update=<c:out value='${payment.paymentID}' />">Update</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/delete/{id}=<c:out value='${payment.paymentID}' />">Delete</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>