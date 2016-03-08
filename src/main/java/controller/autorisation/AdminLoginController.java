package controller.autorisation;

import helper.Params;
import model.Tutor;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/login/admin"})
public class AdminLoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/adminLogin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(!email.equals(Params.ADMIN_EMAIL)&&!password.equals(Params.ADMIN_PASSWORD)){
            resp.sendRedirect("/login/admin");
        }

        User user = new Tutor();
        user.setRole("admin");
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/admin/courses");
    }
}
