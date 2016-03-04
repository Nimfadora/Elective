package controller.student;

import model.Record;
import service.impl.CourseServiceImpl;
import service.impl.RegisterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/search")
public class SearchController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("courses", CourseServiceImpl.getInstance().getStartedCourses());
        req.getRequestDispatcher("/pages/searchPaege.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long courseId = Long.parseLong(req.getParameter("courseId"));
        Long studentId = Long.parseLong(req.getParameter("studentId"));

        Record record = new Record();
        record.setCourseId(courseId);
        record.setStudentId(studentId);
        RegisterServiceImpl.getInstance().addStudent(record);

        doGet(req, resp);
    }
}
