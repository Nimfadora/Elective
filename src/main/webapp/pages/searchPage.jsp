<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../stylesheets/style.css">
    <script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="logo-container">
    <div class="student-logo-container">
        <img class="student-logo" src="../img/logo.png">
    </div>
    <div id="navbar">
        <div class="navbar-button">
            <span class="glyphicon glyphicon-home"></span>
            <a href="/student">Home</a>
        </div>
        <div class="navbar-button">
            <span class="glyphicon glyphicon-search"></span>
            <a href="/student/search">Search</a>
        </div>
    </div>
</div>
<div class="background-container">
    <div class="text-center register-container">
        <div class="sortFilterPanel"></div>
        <div class="panel panel-default home-page-panel">
            <div class="panel-body">
                <div class="status started"></div>
                <div class="row course-container">
                    <p class="course-text">Coursename</p>
                    <p class="course-text">120 students</p>
                    <!-- if(course.status!=start) add button register-->
                    <button class="course-button btn btn-info">More</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
