package ru.laverno.web_service.server;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.laverno.web_service.service.FileService;
import ru.laverno.web_service.servlet.UploadFileServlet;
import ru.laverno.web_service.servlet.DownloadFileServlet;
import ru.laverno.web_service.servlet.FilesServlet;

public class WebServerImpl implements WebServer {

    private final Server server;

    private final Gson gson;

    private final FileService service;

    public WebServerImpl(int port, Gson gson, FileService service) {
        this.gson = gson;
        this.service = service;
        server = new Server(port);
    }

    @Override
    public void start() throws Exception {
        if (server.getHandlers().length == 0) {
            initContext();
        }
        server.start();
    }

    @Override
    public void join() throws Exception {
        server.join();
    }

    @Override
    public void stop() throws Exception {
        server.stop();
    }

    private void initContext() {
        final var servletContextHandler = createServletContextHandler();
        server.setHandler(servletContextHandler);
    }

    private ServletContextHandler createServletContextHandler() {
        final var servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(new UploadFileServlet(service, gson)), "/api/upload");
        servletContextHandler.addServlet(new ServletHolder(new DownloadFileServlet(service, gson)), "/api/download/*");
        servletContextHandler.addServlet(new ServletHolder(new FilesServlet(service, gson)), "/api/files");
        return  servletContextHandler;
    }
}
