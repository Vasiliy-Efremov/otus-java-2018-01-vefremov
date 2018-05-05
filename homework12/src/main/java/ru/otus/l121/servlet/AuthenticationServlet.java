package ru.otus.l121.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationServlet extends HttpServlet {

    private static final String LOGIN_PAGE_TEMPLATE = "authentication.html";
    private final TemplateProcessor templateProcessor;


    public AuthenticationServlet(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    public AuthenticationServlet() throws IOException {
        this(new TemplateProcessor());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        if (true) {
            response.setContentType("text/html;charset=utf-8");
            String page = templateProcessor.getPage(LOGIN_PAGE_TEMPLATE, null);
            response.getWriter().println(page);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (isLoggedIn(request)) {
            response.sendRedirect("/dataFromCache.html");
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
}
