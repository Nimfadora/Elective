package controller.student;

import model.Student;
import service.impl.CourseServiceImpl;
import service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student")
public class StudentHomePageController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = (Student)req.getSession().getAttribute("user");
        req.setAttribute("student", StudentServiceImpl.getInstance().find(student.getId()));
        req.setAttribute("courses", CourseServiceImpl.getInstance().getCoursesByStudent(student.getId()));
        req.getRequestDispatcher("/pages/studentHome.jsp").forward(req, resp);
    }
}
