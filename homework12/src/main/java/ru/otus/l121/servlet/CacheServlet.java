package ru.otus.l121.servlet;

import ru.otus.l121.Cache.CacheEngine;
import ru.otus.l121.base.dataSets.DataSet;

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
    private final TemplateProcessor templateProcessor;


    CacheServlet(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    public CacheServlet() throws IOException {
        this(new TemplateProcessor());
    }

    private static Map<String, Object> createPageVariablesMap() {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("hit", cache.getHitCount());
        pageVariables.put("miss", cache.getMissCount());
        pageVariables.put("maxElements", cache.getValueMaxSize());
        return pageVariables;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        if (true) {
            Map<String, Object> pageVariables = createPageVariablesMap();
            response.setContentType("text/html;charset=utf-8");
            String page = templateProcessor.getPage(CACHE_PAGE_TEMPLATE, pageVariables);
            response.getWriter().println(page);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    public static void setCache(CacheEngine<Long, DataSet> cache) {
        CacheServlet.cache = cache;
    }
}
