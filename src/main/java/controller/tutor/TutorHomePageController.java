package controller.tutor;

import model.Register;
import model.Tutor;
import model.User;
import service.impl.CourseServiceImpl;
import service.impl.RegisterServiceImpl;
import service.impl.TutorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/tutor"})
public class TutorHomePageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("courses", CourseServiceImpl.getInstance().getCoursesByTutor(user.getId()));
        req.setAttribute("tutor", TutorServiceImpl.getInstance().find(user.getId()));
        req.getRequestDispatcher("/pages/tutorHome.jsp").forward(req, resp);
    }
}
