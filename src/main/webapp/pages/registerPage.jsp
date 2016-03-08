<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="i18n.locale" var="lang"/>
<html>
<head>
    <title><fmt:message key="REGISTER" bundle="${lang}"/></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../stylesheets/style.css">
    <script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#save").click(function(){
                var register = {};
                register["courseName"] = $(".header").text();
                register["records"] = createJSON();

                $.ajax({
                    type: "POST",
                    url: "/tutor/register",
                    data: JSON.stringify(register),
                    contentType: "application/json; charset=utf-8",
                    success: function(){ $("#saveStatus").text("All changes were saved successfully").addClass("alert-success");},
                    failure: function(errMsg) {
                        $("#saveStatus").text(errMsg).addClass("alert-danger");
                    }
                });
            });

            function createJSON() {
                var records = [];
                $(".mark").each(function() {

                    var studentId = parseInt($(this).attr("id"));
                    var courseId = parseInt($(this).attr("data-course-id"));
                    var mark = parseInt($(this).val());

                    var record = {};
                    record ["studentId"] = studentId;
                    record["courseId"] = courseId;
                    record ["mark"] = mark;

                    records.push(record);
                });
                return records;
            }
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
    <div class="text-center register-container">
        <p class="header"><c:out value="${courseName}"/></p>
        <p id="saveStatus"></p>
        <div class="table-container">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <td><fmt:message key="STUDENT_ID" bundle="${lang}"/></td>
                    <td><fmt:message key="STUDENT_NAME" bundle="${lang}"/></td>
                    <td><fmt:message key="MARK" bundle="${lang}"/></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${register}" var="record">
                    <tr>
                        <td><c:out value="${record.studentId}"/></td>
                        <td><c:out value="${record.studentName}"/></td>
                        <td><input class="mark form-control" type="number" min="0" max="100" data-course-id="<c:out value="${record.courseId}"/>" value="<c:out value="${record.mark}"/>" id="<c:out value="${record.studentId}"/>"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <button type="button" id="save" class="btn btn-success"><fmt:message key="SAVE" bundle="${lang}"/></button>
    </div>
</div>
</body>
</html>
