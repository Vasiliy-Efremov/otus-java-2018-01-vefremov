package ru.otus.l131.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.l131.Cache.CacheEngine;
import ru.otus.l131.base.DBService;
import ru.otus.l131.base.dataSets.AddressDataSet;
import ru.otus.l131.base.dataSets.DataSet;
import ru.otus.l131.base.dataSets.UserDataSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CacheServlet extends HttpServlet {

    private static final String CACHE_PAGE_TEMPLATE = "dataFromCache.html";
    private static CacheEngine<Long, DataSet> cache;
    @Autowired
    private TemplateProcessor templateProcessor;
    @Autowired
    private DBService dbService;

    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    private static Map<String, Object> createPageVariablesMap() {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("hit", cache.getHitCount());
        pageVariables.put("miss", cache.getMissCount());
        pageVariables.put("maxElements", cache.getValueMaxSize());
        return pageVariables;
    }

    private void loadData() {
        dbService.save(new UserDataSet("Иван", 28, new AddressDataSet("Троицкая")));
        dbService.save(new UserDataSet("Роман", 31, new AddressDataSet("Комитетская")));
        dbService.save(new UserDataSet("Александр", 29, new AddressDataSet("Марата")));

        UserDataSet dataSet = dbService.read(1);
        UserDataSet dataSet2 = dbService.read(2);
        cache = dbService.getCache();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        if (!AuthenticationServlet.isLoggedIn()) {
            response.sendRedirect("/authentication.html");
        } else {
            loadData();
            Map<String, Object> pageVariables = createPageVariablesMap();
            response.setContentType("text/html;charset=utf-8");

            response.getWriter().println(templateProcessor.getPage(CACHE_PAGE_TEMPLATE, pageVariables));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
