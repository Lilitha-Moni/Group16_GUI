<%--@elvariable id="username" type="java.lang.String"--%>
<%--@elvariable id="task" type="za.ac.cput.entity.Member"--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/dashboard/dashboard.css" rel="stylesheet" type="text/css">

</head>

<body>

<div class="sidebar">
    <div class="logo-details">
        <img 
                src="${pageContext.request.contextPath}/resources/img/logo/logo_white.png" 
                style="width: 70%" 
                alt="Logo">
    </div>
    <ul class="nav-links">

        <li>
            <a href="${pageContext.request.contextPath}/" class="active">
                <i class='home'>
                    <img 
                            src="${pageContext.request.contextPath}/resources/img/dashboard/home_icon.png" 
                            alt="Home icon">
                </i>
                <span class="links_name">Home</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/member">
                <i class='member'>
                    <img 
                            src="${pageContext.request.contextPath}/resources/img/dashboard/member_icon.png" 
                            alt="Member icon">
                </i>
                <span class="links_name">Members</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/trainer">
                <i class='trainer'>
                    <img
                            src="${pageContext.request.contextPath}/resources/img/dashboard/trainer_icon.png" 
                            alt="Trainer icon">
                </i>
                <span class="links_name">Trainers</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/schedule">
                <i class='schedule'>
                    <img
                            src="${pageContext.request.contextPath}/resources/img/dashboard/calendar_icon.png" 
                            alt="Calendar icon">
                </i> 
                <span class="links_name">Schedule</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/plans">
                <i class='plans'>
                    <img
                            src="${pageContext.request.contextPath}/resources/img/dashboard/plans_icon.png" 
                            alt="Plans icon">
                </i>
                <span class="links_name">Plans</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/packages">
                <i class='packages'>
                    <img
                            src="${pageContext.request.contextPath}/resources/img/dashboard/packages_icon.png" 
                            alt="Packages icon">
                </i>
                <span class="links_name">Packages</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/equipment">
                <i class='equipment'>
                    <img
                            src="${pageContext.request.contextPath}/resources/img/dashboard/equipment_icon.png" 
                            alt="Equipment icon">
                </i>
                <span class="links_name">Equipment</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/medicalstaff">
                <i class='medicals'>
                    <img
                            src="${pageContext.request.contextPath}/resources/img/dashboard/medicals_icon.png" alt="Medical Staff icon">
                </i>
                <span class="links_name">Medical Staff</span>
            </a>
        </li>

        <li class="sign_out">
            <a href="${pageContext.request.contextPath}/logout">
                <i class='signout'>
                    <img
                            src="${pageContext.request.contextPath}/resources/img/dashboard/signout_icon.png" 
                            alt="Sign out icon">
                </i>
                <span class="links_name">Sign out</span>
            </a>
        </li>
    </ul>

</div>
<section class="home-section">
    <nav>
        <div class="sidebar-button">
            <i class='menu'>
                <img 
                        src="${pageContext.request.contextPath}/resources/img/dashboard/menu_icon.png" 
                        alt="Menu icon">
            </i>
            <span class="dashboard"></span>
        </div>

        <form name="searchTask" method="post">
            <div class="search-box">
                <label for="id"></label>
                <input type="number" id="id" name="id" placeholder="MEMBER" value="${task.memberID}">
                    <i class='search' onclick="searchTaskById()">
                        <img src="${pageContext.request.contextPath}/resources/img/dashboard/search_icon.png" 
                             alt="Search icon">
                    </i>
            </div>
        </form>

        <span id="results"></span>

        <div class="profile-details">
            <img src="${pageContext.request.contextPath}/resources/img/dashboard/profile_icon.png" alt=""> <span
                class="admin_name">${username}</span>
        </div>

    </nav>
    <div class="content">
        <%--@elvariable id="file" type="java.lang.String"--%>
        <jsp:include page="${file}"/>
    </div>
</section>
</body>

<script>
    var dropdown = document.getElementsByClassName("dropdown-btn");
    var i;
    for (i = 0; i < dropdown.length; i++) {
        dropdown[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var dropdownContent = this.nextElementSibling;
            if (dropdownContent.style.display === "block") {
                dropdownContent.style.display = "none";
            } else {
                dropdownContent.style.display = "block";
            }
        });
    }
    let sidebar = document.querySelector(".sidebar");
    let sidebarBtn = document.querySelector(".menu");
    sidebarBtn.onclick = function() {
        sidebar.classList.toggle("active");
        if (sidebar.classList.contains("active")) {
            sidebarBtn.classList.replace("bx-menu", "bx-menu-alt-right");
        } else {
            sidebarBtn.classList.replace("bx-menu-alt-right", "bx-menu");
        }
    }
</script>

</html>