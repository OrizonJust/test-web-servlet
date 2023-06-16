package ru.laverno.web_service.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.laverno.web_service.model.dto.FileRequest;
import ru.laverno.web_service.service.FileService;

import java.io.IOException;
import java.util.stream.Collectors;

public class UploadFileServlet extends HttpServlet {

    private final FileService service;

    private final Gson gson;

    public UploadFileServlet(FileService service, Gson gson) {
        this.service = service;
        this.gson = gson;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var r = req.getReader().lines().collect(Collectors.joining());
        final var file = gson.fromJson(r, FileRequest.class);
        service.uploadFile(file);
    }
}
