package controller.student;

import model.SortFilterParams;
import service.impl.SortFilterService;
import service.impl.TopicService;
import service.impl.TutorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/student/sortFilter"})
public class SortFilterController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SortFilterParams params = new SortFilterParams();

        params.setSortCriteria(req.getParameter("sortType"));
        params.setTopicId(Long.parseLong(req.getParameter("topicId")));
        params.setTutorId(Long.parseLong(req.getParameter("tutorId")));
        req.setAttribute("courses", SortFilterService.getInstance().getAll(params));
        req.setAttribute("topics", TopicService.getInstance().getAll());
        req.setAttribute("tutors", TutorServiceImpl.getInstance().getAll());
        req.getRequestDispatcher("/pages/searchPage.jsp").forward(req, resp);

    }
}
