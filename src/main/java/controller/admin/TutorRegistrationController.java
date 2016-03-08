package controller.admin;

import model.Course;
import model.Student;
import model.Tutor;
import service.impl.CourseServiceImpl;
import service.impl.TutorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/admin/tutors"})
public class TutorRegistrationController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("courses", CourseServiceImpl.getInstance().getUnassigned());
        req.getRequestDispatcher("/pages/adminTutors.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Tutor tutor = new Tutor();
        tutor.setName(req.getParameter("name"));
        tutor.setEmail(req.getParameter("email"));
        tutor.setPassword(req.getParameter("password"));


        Long tutorId = TutorServiceImpl.getInstance().create(tutor);
        Long courseId = Long.parseLong(req.getParameter("courseId"));

        CourseServiceImpl.getInstance().setTutor(courseId, tutorId);

        doGet(req, resp);
    }
}
