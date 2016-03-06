package controller.autorisation;

import helper.Params;
import model.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login/student")
public class StudentLogInController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email= req.getParameter("email");
        String password = req.getParameter("password");

        Student student = StudentServiceImpl.getInstance().authorise(email, password);

        if(student == null)
            throw  new NullPointerException();

        req.getSession().setAttribute("user", student);

        resp.sendRedirect("/student");
    }
}
