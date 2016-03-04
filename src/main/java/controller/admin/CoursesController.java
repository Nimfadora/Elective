package controller.admin;

import model.Course;
import service.impl.CourseServiceImpl;
import service.impl.StatusService;
import service.impl.TopicService;
import service.impl.TutorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/courses")
public class CoursesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("courses", CourseServiceImpl.getInstance().getAll());
        req.setAttribute("topics", TopicService.getInstance().getAll());
        req.setAttribute("tutors", TutorServiceImpl.getInstance().getAll());
        req.setAttribute("statuses", StatusService.getInstance().getAll());

        req.getRequestDispatcher("/pages/adminCourses.jsp").forward(req, resp);
    }


    //update
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Course course = new Course();
        course.setId(Long.parseLong(req.getParameter("id")));
        course.setName(req.getParameter("name"));
        course.setDuration(Integer.parseInt(req.getParameter("duration")));
        course.setTopicId(Long.parseLong(req.getParameter("topicId")));
        course.setTutorId(Long.parseLong(req.getParameter("tutorId")));
        course.setStatus(req.getParameter("status"));

        CourseServiceImpl.getInstance().update(course);

        doGet(req, resp);
    }
}
