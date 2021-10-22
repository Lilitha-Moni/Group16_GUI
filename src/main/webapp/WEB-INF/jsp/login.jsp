<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <link href="../resources/css/login/login.css" rel="stylesheet" type="text/css">

    <script type="text/javascript">
        function validateLogins()
        {
            //--------Email
            var email = document.getElementById("email").value;
            if (email == null || email === "") {
                document.getElementById("emailErr").innerHTML = " Email field cannot be left empty!";
            } else {
                document.getElementById("emailErr").innerHTML = " ";
            }
            //--------Password
            var password = document.getElementById("password").value;
            if (password == null || password === "") {
                document.getElementById("passwordErr").innerHTML = " Password cannot be blank!";
            } else {
                document.getElementById("passwordErr").innerHTML = " ";
            }
        }
    </script>
</head>
<body>
<form action="/login" method="POST" >
    <div class="login">
        <div class="login-screen">
            <div class="app-title">
                <h1>Login</h1>
            </div>
            <c:if test='${param.user.equals("not-found")}'>
                <div class="warning"><span>User not found</span></div>
            </c:if>
            <div class="login-form">
                <div class="control-group">
                    <input type="text" class="login-field" value="" placeholder="email/username" id="username" name="username" required>
                    <label class="login-field-icon fui-user" for="username"></label>
                </div>

                <div class="control-group">
                    <input type="password" class="login-field" value="" placeholder="password" id="password" name="password" required>
                    <label class="login-field-icon fui-lock" for="password"></label>
                </div>

                <button type="submit" class="btn">LOGIN</button>
<%--                <a class="btn btn-primary btn-large btn-block" href="#">LOGIN</a>--%>
                <a class="login-link" href="#">Lost your password?</a>
            </div>
        </div>
    </div>
</form>

</body>
</html>