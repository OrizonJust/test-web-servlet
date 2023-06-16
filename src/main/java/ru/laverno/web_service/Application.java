package ru.laverno.web_service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.laverno.web_service.server.WebServer;
import ru.laverno.web_service.server.WebServerImpl;
import ru.laverno.web_service.service.FileService;
import ru.laverno.web_service.service.FileServiceImpl;
import ru.laverno.web_service.validator.FileValidator;

public class Application {

    public static void main(String[] args) throws Exception {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        FileValidator validator = new FileValidator();
        FileService service = new FileServiceImpl(validator);
        WebServer server = new WebServerImpl(8080, gson, service);

        server.start();
        server.join();
    }
}
