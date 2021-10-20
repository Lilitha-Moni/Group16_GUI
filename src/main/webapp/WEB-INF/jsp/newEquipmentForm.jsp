<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Register</title>
    <link href="../resources/css/form_style.css" rel="stylesheet" type="text/css">

    <script type="text/javascript">

        function validateform() {
            var valid = true;

            //--------Name
            var Name = document.getElementById("Name").value;
            if (Name == null || Name == "") {
                document.getElementById("NameErr").innerHTML = " Equipment name field cannot be left empty";
                valid = false;

            } else if (/^[a-zA-Z,.-]{2,50}$/.test(Name) == false) {
                document.getElementById("NameErr").innerHTML = " Please provide a valid equipment name";
                valid = false;
            } else {
                document.getElementById("NameErr").innerHTML = " ";
                valid = true;
            }
            //--------Model
            var lastName = document.getElementById("Model").value;
            if (Model == null || Model == "") {
                document.getElementById("ModelErr").innerHTML = " Model field cannot be left empty";
                valid = false;
            } else if (/^[a-zA-Z,.-]{2,50}$/.test(Model) == false) {
                document.getElementById("ModelErr").innerHTML = " Please provide a valid model";
                valid = false;
            } else {
                document.getElementById("ModelErr").innerHTML = " ";
                valid = true;
            }

            //--------Info
            var Info = document.getElementById("Info").value;
            if (Info == null || Info == "") {
                document.getElementById("InfoErr").innerHTML = " Info field cannot be left empty";
                valid = false;
            } else if (/^[a-zA-Z,.-]{2,50}$/.test(Info) == false) {
                document.getElementById("InfoErr").innerHTML = " Please provide a valid info";
                valid = false;
            } else {
                document.getElementById("InfoErr").innerHTML = " ";
                valid = true;
            }
        }

    </script>



</head>
<body>
<form:form action="/equipment/create" modelAttribute="equipment" method="post" onsubmit="return validateform();"   >
         <!--
                </div>
                <div class="login-form">
                    <table>
                      <div class="control-group">
                            <tr>
                                <th>Equipment Name:</th>
                                <td><input type="text" name="Name" id="Name" size="45" value="${equipment.Name}"
                                           onblur="validateform()" />
                                    <span id="NameErr" style="color: red"> </span><br></td>
                            </tr>
                        </div>

                        <div class="control-group">
                            <tr>
                                <th>Model:</th>
                                <td><input type="text" name="Model" id="Model"
                                           size="45" value="${equipment.Model}"
                                           onblur="validateform()" /><span id="ModelErr"
                                                                           style="color: red"> </span><br></td>
                            </tr>
                        </div>
                        <div class="control-group">
                            <tr>
                                <th>Info:</th>
                                <td><input type="text" name="Info" id="Info" size="45"
                                           value="${equipment.Info}"
                                           onblur="validateform()" /><span id="InfoErr" style="color: red">
							</span> <br></td>
                            </tr>
                        </div>

                        <tr>

                            <td colspan="2" align="center"><input type="submit" class="btn btn-primary btn-large btn-block" value="Save" /> </br>
                                <button type="reset" class="btn btn-primary btn-large btn-block"
                                        value="Reset">Reset</button></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    -->

    </div>
    <div class="login-form">
        <table>
            <div class="control-group">
                <tr>
                    <th>First Name:</th>
                    <td><form:input type="text" id="Name" path="Name" onblur="validateform()"/></td>
                        <form:errors path="Name" />
                </tr>
            </div>
            <div class="control-group">
                <tr>
                    <th>Last Name:</th>
                    <td><form:input type="text" id="Model" path="Model"  onblur="validateform()"/> </td>
                    <form:errors path="Model" />
                </tr>
        </div>
            <div class="control-group">
                <tr>
                    <th>Age:</th>
                    <td> <form:input type="text" id="Info" path="Info"  onblur="validateform()"/></td>
                        <form:errors path="Info" />
                </tr>
        </div>

            <tr>
                <td colspan="2" align="center"><input type="submit" class="btn btn-primary btn-large btn-block" value="Save" /> </br>
                    <button type="reset" class="btn btn-primary btn-large btn-block" value="Reset">Reset</button></td>
            </tr>
        </table>
    </div>
    </div>
    </div>
</form:form>

</body>
</html>