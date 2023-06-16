package ru.laverno.web_service.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.laverno.web_service.service.FileService;

import java.io.IOException;

public class DownloadFileServlet extends HttpServlet {

    private final FileService service;

    private final Gson gson;

    public DownloadFileServlet(FileService service, Gson gson) {
        this.service = service;
        this.gson = gson;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var url = req.getRequestURL().toString();
        final var id = url.substring(url.lastIndexOf("/") + 1);
        final var result = service.getFile(id);
        final var json = gson.toJson(result);

        final var out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.println(json);
        out.flush();
    }
}
