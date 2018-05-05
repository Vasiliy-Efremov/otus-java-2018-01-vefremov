package ru.otus.l121.servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class MyServer {
    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";

    public static void start() throws Exception {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(AuthenticationServlet.class, "/authentication.html");
        context.addServlet(CacheServlet.class, "/dataFromCache.html");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }
}
