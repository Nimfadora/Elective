<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="i18n.locale" var="lang"/>
<html>
<head>
    <title><fmt:message key="HOME" bundle="${lang}"/></title>
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
            <a href="/student"><fmt:message key="HOME" bundle="${lang}"/></a>
        </div>
        <div class="navbar-button">
            <span class="glyphicon glyphicon-search"></span>
            <a href="/student/search"><fmt:message key="SEARCH" bundle="${lang}"/></a>
        </div>
    </div>
</div>
<div class="background-container">
    <div class="container tutor-page-conteiner">
        <div class="panel panel-default home-page-panel student-page-container">
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
                    <div class="text-center"><h3 class="profile-text"><fmt:message key="COURSES" bundle="${lang}"/></h3></div>
                </div>

                <hr/>

                <div class="filter-panel text-center">
                    <label class="checkbox-inline"><input type="checkbox" id="started" value="" checked><fmt:message key="STARTED" bundle="${lang}"/></label>
                    <label class="checkbox-inline"><input type="checkbox" id="inprogress" value="" checked><fmt:message key="IN_PROGRESS" bundle="${lang}"/></label>
                    <label class="checkbox-inline"><input type="checkbox" id="finished" value="" checked><fmt:message key="FINISHED" bundle="${lang}"/></label>
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
                                        <p class="course-text"><fmt:message key="MARK" bundle="${lang}"/>:<c:out value="${course.mark}"/></p>
                                    </c:when>
                                </c:choose>
                                <button class="course-button btn btn-info"><fmt:message key="MORE" bundle="${lang}"/></button>
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
