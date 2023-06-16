package ru.laverno.web_service.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.laverno.web_service.service.FileService;

import java.io.IOException;

public class FilesServlet extends HttpServlet {

    private final FileService service;

    private final Gson gson;

    public FilesServlet(FileService service, Gson gson) {
        this.service = service;
        this.gson = gson;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var result = service.getFiles();
        final var json = gson.toJson(result);

        final var out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.println(json);
        out.flush();
    }
}
