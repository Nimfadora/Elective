<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
  <title>Courses</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="../stylesheets/admin.css">
  <script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
  <script type="text/javascript" src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="../js/adminCourse.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <img src="../img/logo.png">
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="/admin/courses">Courses</a></li>
      <li><a href="/admin/tutors">Tutors</a></li>
      <li><a href="/admin/students">Students</a></li>
    </ul>
  </div>
</nav>
<div class="page-container">
  <div class="left-container">
    <div class="text-center">
      <p class="table-name">Courses</p>
      <table class="table table-striped .table-bordered">
        <thead>
        <tr>
          <th class="text-center">Name</th>
          <th class="text-center">Duration</th>
          <th class="text-center">Topic</th>
          <th class="text-center">Tutor</th>
          <th class="text-center">Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="course" items="${courses}">
          <tr id="<c:out value="${course.id}"/>">
            <td><c:out value="${course.name}"/></td>
            <td><c:out value="${course.duration}"/></td>
            <td id="<c:out value="${course.topicId}"/>"><c:out value="${course.topic}"/></td>
            <td id="<c:out value="${course.tutorId}"/>"><c:out value="${course.tutor}"/></td>
            <td><c:out value="${course.status}"/></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
  <div class="right-container">
    <p class="table-name text-center">Editor</p>
    <div class="form-container">
      <form role="form" method="post">
        <input type="hidden" name="id" class="identifier" >
        <div class="form-group">
          <label for="coursename">Coursename</label>
          <input type="text" name="name" id="coursename" class="form-control">
        </div>
        <div class="form-group">
          <label for="duration">Duration (hours)</label>
          <input type="number" name="duration" id="duration" class="form-control">
        </div>
        <div class="form-group">
          <label for="topic">Topic</label>
          <input type="hidden" name="topicId" id="topicId">
          <select class="form-control" id="topic">
            <c:forEach var="topic" items="${topics}">
              <option id="<c:out value="${topic.id}"/>"><c:out value="${topic.title}"/></option>
            </c:forEach>
          </select>
        </div>
        <div class="form-group">
          <label for="tutor">Tutor</label>
          <input type="hidden" name="tutorId" id="tutorId">
          <select class="form-control" id="tutor">
              <option id="-1"> - </option>
            <c:forEach var="tutor" items="${tutors}">
              <option id="<c:out value="${tutor.id}"/>"><c:out value="${tutor.name}"/></option>
            </c:forEach>
          </select>
        </div>
        <div class="form-group">
          <label for="status">Status</label>
          <select class="form-control" id="status" name="status">
            <c:forEach var="status" items="${statuses}">
              <option><c:out value="${status}"/></option>
            </c:forEach>
          </select>
        </div>
        <button type="button" id="create" class="btn btn-success">Create</button>
        <button type="button" id="update" class="btn btn-info">Update</button>
        <button type="button" id="delete" class="btn btn-danger">Delete</button>
        <button type="button" id="clear" class="btn btn-default">Clear form</button>
      </form>
    </div>
  </div>
</div>

</body>
</html>
