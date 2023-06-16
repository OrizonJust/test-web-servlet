package ru.laverno.web_service.server;

public interface WebServer {

    void start() throws Exception;

    void join() throws Exception;

    void stop() throws Exception;
}
