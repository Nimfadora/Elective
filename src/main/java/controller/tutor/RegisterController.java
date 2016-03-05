package controller.tutor;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Register;
import service.impl.RegisterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/tutor/register")
public class RegisterController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long courseId = Long.parseLong(req.getParameter("courseId"));
        Register register = RegisterServiceImpl.getInstance().getRegister(courseId);

        req.setAttribute("register", register.getRecords());
        req.setAttribute("courseName", register.getCourseName());

        req.getRequestDispatcher("/pages/registerPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Register register = mapper.readValue(req.getInputStream(), Register.class);
        RegisterServiceImpl.getInstance().updateRegister(register);
    }
}
