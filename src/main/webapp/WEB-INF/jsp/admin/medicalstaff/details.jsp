<%--@elvariable id="medicalstaff" type="za.ac.cput.entity.MedicalStaff"--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Medical Staff</h1>
<h3>View details</h3>
<br>
<p>Medical Staff ID: <span>${medicalstaff.id}</span></p>
<p>First name: <span>${medicalstaff.firstName}</span></p>
<p>Last name: <span>${medicalstaff.lastName}</span></p>
<p>Phone Number: <span>${medicalstaff.phoneNumber}</span></p>
<p>Email: <span>${medicalstaff.email}</span></p>
<p>Checkups Done: <span>${medicalstaff.checkupsDone}</span></p>
<p>Fitness Tests Done: <span>${medicalstaff.fitnessTestsDone}</span></p>
<p>Medical Emergencies Handled: <span>${medicalstaff.medicalEmergenciesDone}</span></p>
<a href="/medicalstaff/update/${medicalstaff.id}">Update</a>
<a href="/medicalstaff/delete/${medicalstaff.id}" onclick="return confirm('Are you sure you want to remove this user as a member of the medical staff for this gym?');">Delete</a>