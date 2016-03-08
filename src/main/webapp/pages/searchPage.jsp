<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="i18n.locale" var="lang"/>
<html>
<head>
    <title><fmt:message key="SEARCH" bundle="${lang}"/></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../stylesheets/style.css">
    <script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#apply").click(function () {
                $("#topicId").val($("#topic option:selected").attr('id'));
                $("#tutorId").val($("#tutor option:selected").attr('id'));
                $("#sortType").val($("#sort option:selected").attr('id'));
                $("form").attr("action", "/student/sortFilter").submit();
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
    <div class="text-center search-container">
        <div class="sortFilterPanel">
            <div class="form-inline">
                <form action="/student/sortFilter" method="get">
                    <div class="form-group">
                        <label for="sort"><fmt:message key="SORT" bundle="${lang}"/></label>
                        <input type="hidden" name="sortType" id="sortType">
                        <select class="form-control" id="sort">
                            <option id="no">-</option>
                            <option id="nameAsc"><fmt:message key="A_Z" bundle="${lang}"/></option>
                            <option id="nameDesc"><fmt:message key="Z_A" bundle="${lang}"/></option>
                            <option id="durationAsc"><fmt:message key="DURATION_ASC" bundle="${lang}"/></option>
                            <option id="durationDesc"><fmt:message key="DURATION_DESC" bundle="${lang}"/></option>
                            <option id="popularityDesc"><fmt:message key="POPULARITY_ASC" bundle="${lang}"/></option>
                            <option id="popularityAsc"><fmt:message key="POPULARITY_DESC" bundle="${lang}"/></option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="topic"><fmt:message key="TOPIC" bundle="${lang}"/></label>
                        <input type="hidden" name="topicId" id="topicId">
                        <select class="form-control" id="topic">
                            <option id="-1">-</option>
                            <c:forEach var="topic" items="${topics}">
                                <option id="<c:out value="${topic.id}"/>"><c:out value="${topic.title}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="tutor"><fmt:message key="TUTOR" bundle="${lang}"/></label>
                        <input type="hidden" name="tutorId" id="tutorId">
                        <select class="form-control" id="tutor">
                            <option id="-1">-</option>
                            <c:forEach var="tutor" items="${tutors}">
                                <option id="<c:out value="${tutor.id}"/>"><c:out value="${tutor.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="button" id="apply" class="btn btn-success"><fmt:message key="APPLY" bundle="${lang}"/></button>
                </form>
            </div>
        </div>
        <div style="padding-top: 50px">
            <c:forEach items="${courses}" var="course">
                <div class="panel panel-default home-page-panel">
                    <div class="panel-body">
                        <div class="row course-container">
                            <p class="course-text"><c:out value="${course.name}"/></p>

                            <p class="course-text"><c:out value="${course.numOfStudents}"/></p>

                            <p class="course-text"><c:out value="${course.tutor}"/></p>

                            <p class="course-text"><c:out value="${course.duration}"/></p>
                            <button class="course-button btn btn-success"><fmt:message key="ENROLL" bundle="${lang}"/></button>
                            <button class="course-button btn btn-info"><fmt:message key="MORE" bundle="${lang}"/></button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
