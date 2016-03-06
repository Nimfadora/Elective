<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../stylesheets/style.css">
    <script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#started").change(function() {
                if(this.checked)
                    $(".started").parents(".course-panel").hide();
                else
                    $(".started").parents(".course-panel").show();

            });
            $("#inprogress").change(function() {
                if(this.checked)
                    $(".inprogress").parents(".course-panel").show();
                else
                    $(".inprogress").parents(".course-panel").hide();
            });
            $("#finished").change(function() {
                if(this.checked)
                    $(".finished").parents(".course-panel").show();
                else
                    $(".finished").parents(".course-panel").hide();
            });
        });
    </script>
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
    <div class="container tutor-page-conteiner">
        <div class="panel panel-default home-page-panel">
            <div class="panel-body">
                <div class="row">
                    <div class="span2"><img src="../img/student.png" class="img-rounded avatar" alt="profile photo">
                    </div>
                    <div class="span4">
                        <div style="width: 100%"><p class="profile-text"><c:out value="${student.name}"/></p></div>
                        <div style="width: 100%"><p class="profile-text"><c:out value="${student.age}"/></p></div>
                        <div style="width: 100%"><p class="profile-text"><c:out value="${student.email}"/></p></div>
                    </div>
                </div>

                <div class="row">
                    <div class="text-center"><h3 class="profile-text">Courses</h3></div>
                </div>

                <hr/>

                <div class="filter-panel text-center">
                    <label class="checkbox-inline"><input type="checkbox" id="started" value="" checked>Started</label>
                    <label class="checkbox-inline"><input type="checkbox" id="inprogress" value="" checked>In progress</label>
                    <label class="checkbox-inline"><input type="checkbox" id="finished" value="" checked>Finished</label>
                </div>

                <hr/>

                <c:forEach var="course" items="${courses}">
                    <div class="panel course-panel panel-default home-page-panel">
                        <div class="panel-body">
                            <div class="status <c:out value="${course.status}"/>"></div>
                            <div class="row course-container">
                                <p class="course-text"><c:out value="${course.name}"/></p>

                                <p class="course-text"><c:out value="${course.tutor}"/></p>
                                <c:choose>
                                    <c:when test="${course.mark != null}">
                                        <p class="course-text">Mark:<c:out value="${course.mark}"/></p>
                                    </c:when>
                                </c:choose>
                                <button class="course-button btn btn-info">More</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
</div>
</body>
</html>
