package ru.otus.l131.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationServlet extends HttpServlet {

    private static final String LOGIN_PAGE_TEMPLATE = "authentication.html";
    @Autowired
    private TemplateProcessor templateProcessor;

    private static boolean isLoggedIn = false;

    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println(templateProcessor.getPage(LOGIN_PAGE_TEMPLATE, null));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (isLoggedIn(request)) {
            response.sendRedirect("/dataFromCache.html");
            isLoggedIn = true;
        } else {
            response.sendRedirect("/index.html");
        }
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        String login = request.getParameter("Login");
        String password = request.getParameter("Password");
        if (null != login && null != password) {
            if ("admin".equals(login) && "qwerty".equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }
}
