package ru.laverno.web_service.service;

import ru.laverno.web_service.model.dto.FileRequest;
import ru.laverno.web_service.model.entity.File;

import java.util.List;

public interface FileService {

    File uploadFile(FileRequest fileRequest);

    File getFile(String id);

    List<File> getFiles();
}
