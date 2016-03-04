<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Register</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../stylesheets/style.css">
    <script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="logo-container">
    <div class="text-center">
        <img class="logo" src="../img/logo.png">
    </div>
</div>
<div class="background-container">
    <div class="text-center register-container">
        <p class="header"><c:out value="${courseName}"/></p>
        <div class="table-container">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <td>ID</td>
                    <td>Student Name</td>
                    <td>Mark</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${register}" var="record">
                    <tr>
                        <td><c:out value="${record.id}"/></td>
                        <td><c:out value="${record.studentName}"/></td>
                        <td><c:out value="${record.Mark}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <button type="button" class="btn btn-success">Save</button>
    </div>
</div>
</body>
</html>
