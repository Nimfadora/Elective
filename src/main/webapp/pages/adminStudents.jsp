<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Students</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="../stylesheets/admin.css">
  <script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
  <script type="text/javascript" src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <img src="../img/logo.png">
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/admin/courses">Courses</a></li>
      <li><a href="/admin/tutors">Tutors</a></li>
      <li class="active"><a href="/admin/students">Students</a></li>
    </ul>
  </div>
</nav>
<div class="page-container">
  <div class="container">
    <div class="text-center">
      <p class="table-name">Students</p>
      <table class="table table-striped .table-bordered">
        <thead>
        <tr>
          <th class="text-center">Name</th>
          <th class="text-center">Email</th>
          <th class="text-center">Ban</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${students}">
          <tr>
            <td><c:out value="${student.name}"/></td>
            <td><c:out value="${student.email}"/></td>
            <td>
              <form role="form" action="/admin/students" method="post">
                <input type="hidden" name="id" value="<c:out value="${student.id}"/>">
                <c:choose>
                  <c:when test="${student.ban=='TRUE'}">
                    <button class="btn btn-success" type="submit">Lift ban</button>
                  </c:when>
                  <c:otherwise>
                    <button class="btn btn-danger" type="submit">Ban</button>
                  </c:otherwise>
                </c:choose>
              </form>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>
