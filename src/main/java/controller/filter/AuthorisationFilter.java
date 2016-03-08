package controller.filter;

import model.User;
import service.impl.AuthService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/student/*", "/tutor/*", "/admin/*"})
public class AuthorisationFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getRequestURL().toString();
        User user = (User)req.getSession().getAttribute("user");
        String outputURL = AuthService.getOutputURL(url, user);
        if(outputURL.equals(url))
            chain.doFilter(req, resp);
        resp.sendRedirect(outputURL);
    }

    @Override
    public void destroy() {

    }
}
