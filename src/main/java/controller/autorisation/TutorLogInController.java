package controller.autorisation;

import helper.Params;
import model.Tutor;
import service.impl.CourseServiceImpl;
import service.impl.TutorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login/tutor")
public class TutorLogInController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email= req.getParameter("email");
        String password = req.getParameter("password");

        if(email.equals(Params.ADMIN_EMAIL))
            req.getRequestDispatcher("/login/admin").forward(req, resp);


        Tutor tutor = TutorServiceImpl.getInstance().authorise(email, password);

        if(tutor == null)
            throw  new NullPointerException();
        req.getSession().setAttribute("user", tutor);

        req.setAttribute("courses", CourseServiceImpl.getInstance().getCoursesByTutor(tutor.getId()));
        req.setAttribute("tutor", TutorServiceImpl.getInstance().find(tutor.getId()));
        req.getRequestDispatcher("/pages/tutorHome.jsp").forward(req, resp);


    }
}
