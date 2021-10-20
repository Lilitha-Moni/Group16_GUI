<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Members</title>
    <link href="../resources/css/table_style.css" rel="stylesheet" type="text/css">

</head>
<body>

<h2>Members </h2>

<a href="/WorkOut Schedule/new">
    <button type="Enter">Add new WorkOut Schedule</button>
</a>
<br/><br/>

<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th>Schedule Id</th>
            <th>Trainer Id</th>
            <th>Member Id</th>
            <th>WorkOut Id</th>
            <th>Date</th>
            <th>Time</th>
        </tr>

        </thead>

        <c:forEach items="${listWorkOutSchedule}" var="WorkOutSchedule">
            <tbody>
            <tr>
                <td>${WorkOutSchedule.ScheduleId}</td>
                <td>${WorkOutSchedule.TrainerId} ${WorkOutSchedule.MemberId}${WorkOutSchedule.WorkOutId}${WorkOutSchedule.Date}${WorkOutSchedule.Time}</td>
                <td><a
                        href="/read/{id}=<c:out value='${WorkOutSchedule.ScheduleId}' />">View</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                            href="/update=<c:out value='${WorkOutSchedule.ScheduleId}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                            href="/delete/{id}=<c:out value='${WorkOutSchedule.ScheduleId}' />">Delete</a></td>

            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>

</body>
</html>