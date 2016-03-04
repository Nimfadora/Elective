<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Tutors</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="../stylesheets/admin.css">
  <script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
  <script type="text/javascript" src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
  <script>
    $(document).ready(function() {
      $("#register").click(function(){
        $("#courseId").val($("#course option:selected").attr('id'));
        $("form").attr("action", "/admin/tutors").submit();
      });

    });
  </script>
</head>
<body>
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <img src="../img/logo.png">
      </div>
      <ul class="nav navbar-nav">
        <li><a href="/admin/courses">Courses</a></li>
        <li class="active"><a href="/admin/tutors">Tutors</a></li>
        <li><a href="/admin/students">Students</a></li>
      </ul>
    </div>
  </nav>
  <div class="page-container">
    <div class="container">
      <p class="table-name text-center">Register new tutor</p>
      <div class="form-container">
        <form role="form" action="/admin/tutors" method="post">
          <div class="form-group">
            <label for="name">Full name</label>
            <input type="text" name="name" id="name" class="form-control">
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <input type="email" name="email" id="email" class="form-control">
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" id="password" class="form-control">
          </div>
          <div class="form-group">
            <label for="confirm">Confirm password</label>
            <input type="password" id="confirm" class="form-control">
          </div>

          <div class="form-group">
            <label for="course">Course</label>
            <input type="hidden" name="courseId" id="courseId">
            <select class="form-control" id="course">
              <c:forEach var="course" items="${courses}">
                <option id="<c:out value="${course.id}"/>"><c:out value="${course.name}"/></option>
              </c:forEach>
            </select>
          </div>

          <button type="submit" id="register" class="btn btn-success">Register</button>
          <button type="button" id="clear" class="btn btn-default">Clear form</button>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
