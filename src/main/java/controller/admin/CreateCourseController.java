package controller.admin;

import model.Course;
import service.impl.CourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/admin/courses/create"})
public class CreateCourseController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Course course = new Course();
        course.setName(req.getParameter("name"));
        course.setDuration(Integer.parseInt(req.getParameter("duration")));
        course.setTopicId(Long.parseLong(req.getParameter("topicId")));
        course.setTutorId(Long.parseLong(req.getParameter("tutorId")));
        course.setStatus(req.getParameter("status"));

        CourseServiceImpl.getInstance().create(course);
        resp.sendRedirect("/admin/courses");
    }
}
