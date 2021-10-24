<%--@elvariable id="medicalstaff" type="za.ac.cput.entity.MedicalStaff"--%>
<%--@elvariable id="subheading" type="java.lang.String"--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Medical Staff</h1>
<h3>${subheading}</h3>
<form method="POST">
    <input type="hidden" name="id" value="${empty medicalstaff ? medicalstaff.id : 1}">
    <input type="hidden" name="checkups_done" value="${empty medicalstaff ? medicalstaff.checkupsDone : 0}">
    <input type="hidden" name="medical_emergencies_done" value="${empty medicalstaff ? medicalstaff.checkupsDone : 0}">

    <label for="ms_firstname">First name</label><br>
    <input type="text" name="firstName" id="ms_firstname" value="${medicalstaff.firstName}"><br>

    <label for="ms_lastname">Last name</label><br>
    <input type="text" name="lastName" id="ms_lastname" value="${medicalstaff.lastName}"><br>

    <label for="ms_email">Email</label><br>
    <input type="text" name="email" id="ms_email" value="${medicalstaff.email}"><br>

    <label for="ms_phone_number">Phone number</label><br>
    <input type="text" name="phoneNumber" id="ms_phone_number" value="${medicalstaff.phoneNumber}"><br>
    <br>
    <button type="submit">Submit</button>
</form>