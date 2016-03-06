package controller.autorisation;

import model.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp/student")
public class StudentSignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email= req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
//        int age = Integer.parseInt(req.getParameter("age"));

        Student student = new Student();
        student.setEmail(email);
        student.setPassword(password);
        student.setName(name);
//        student.setAge(age);
        StudentService service = StudentServiceImpl.getInstance();
        student.setId(service.create(student));

        req.getSession().setAttribute("user", student);

        req.getRequestDispatcher("").forward(req, resp);
    }
}
