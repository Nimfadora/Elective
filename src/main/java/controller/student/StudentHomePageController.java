package controller.student;

import model.Student;
import model.User;
import service.impl.CourseServiceImpl;
import service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/student"})
public class StudentHomePageController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        req.setAttribute("student", StudentServiceImpl.getInstance().find(user.getId()));
        req.setAttribute("courses", CourseServiceImpl.getInstance().getCoursesByStudent(user.getId()));
        req.getRequestDispatcher("/pages/studentHome.jsp").forward(req, resp);
    }
}
