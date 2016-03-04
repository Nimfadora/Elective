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
    private StudentService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email= req.getParameter("email");
        String password = req.getParameter("password");

        if(email.equals(Params.ADMIN_EMAIL))
            req.getRequestDispatcher("/login/admin").forward(req, resp);

        StudentService service = StudentServiceImpl.getInstance();
        Student student = service.authorise(email, password);

        if(student == null)
            throw  new NullPointerException();

        req.getRequestDispatcher("/pages/studentHome.jsp").forward(req, resp);


    }
}
