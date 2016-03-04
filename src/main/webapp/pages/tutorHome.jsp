<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../stylesheets/style.css">
    <script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $(".course-button").click(function (e) {
                $.get("/tutor/register/get", { courseId: parseInt($(".courseId").val()) } );
            });
        });
    </script>
</head>
<body>
<div class="logo-container">
    <div class="text-center">
        <img class="logo" src="../img/logo.png">
    </div>
</div>
    <div class="background-container">
        <div class="container tutor-page-conteiner">
            <div class="panel panel-default home-page-panel">
                <div class="panel-body">
                    <div class="row">
                        <div class="span2"><img src="../img/man-tutor.gif" class="img-rounded avatar" alt="profile photo"></div>
                        <div class="span4">
                            <div style="width: 100%"><p class="profile-text"><c:out value="${tutor.name}"/></p></div>
                            <div style="width: 100%"><p class="profile-text"><c:out value="${tutor.email}"/></p></div>
                        </div>
                    </div>

                    <div class="row"><div class="text-center"><h3 class="profile-text">Courses</h3></div></div>

                    <hr/>
                    <c:forEach items="${courses}" var="course">
                        <div class="panel panel-default home-page-panel">
                            <div class="panel-body">
                                <div class="status <c:out value="${course.status}"/>"></div>
                                <div class="course-container">
                                    <p class="course-text"><c:out value="${course.name}"/></p>
                                    <p class="course-text"><c:out value="${course.numOfStudents}"/> Students</p>
                                    <c:choose>
                                        <c:when test="${course.status == 'finished'}">
                                            <%--<form action="/tutor" method="post" style="margin-bottom: 0;">--%>
                                                <button type="button" class="course-button btn btn-info">
                                                    <span style="margin-right: 5px;">Register</span><span class="glyphicon glyphicon-th-list"></span>
                                                </button>
                                                <input type="hidden" name="courseId" class="courseId" value="<c:out value="${course.id}"/> ">
                                            <%--</form>--%>
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