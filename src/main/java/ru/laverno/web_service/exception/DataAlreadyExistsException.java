package ru.laverno.web_service.exception;

public class DataAlreadyExistsException extends RuntimeException {

    public DataAlreadyExistsException(String message) {
        super(message);
    }
}
