package controller.autorisation;

import helper.Params;
import model.Tutor;
import model.User;
import service.impl.CourseServiceImpl;
import service.impl.TutorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/login/tutor"})
public class TutorLogInController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email= req.getParameter("email");
        String password = req.getParameter("password");

        User user = TutorServiceImpl.getInstance().authorise(email, password);

        if(user == null)
            throw  new NullPointerException();
        req.getSession().setAttribute("user", user);

        resp.sendRedirect("/tutor");
    }
}
