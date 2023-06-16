package ru.laverno.web_service.validator;

public class FileValidator {

    public boolean validFileSize(int fileSize) {
        return fileSize <= 100 * 1024;
    }

    public boolean validFileExtension(String fileName) {
        final var extension = fileName.substring(fileName.lastIndexOf("."));

        return extension.equals(".txt") || extension.equals(".csv");
    }
}
