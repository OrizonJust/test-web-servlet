package ru.laverno.web_service.service;

import ru.laverno.web_service.exception.DataAlreadyExistsException;
import ru.laverno.web_service.exception.ValidationException;
import ru.laverno.web_service.model.dto.FileRequest;
import ru.laverno.web_service.model.entity.File;
import ru.laverno.web_service.validator.FileValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileServiceImpl implements FileService {

    private FileValidator validator;

    private Map<String, File> database;

    public FileServiceImpl(FileValidator validator) {
        this.validator = validator;
        this.database = new HashMap<>();
    }

    @Override
    public File uploadFile(FileRequest fileRequest) {
        if (!validator.validFileSize(fileRequest.getFileSize()) || !validator.validFileExtension(fileRequest.getFileName())) {
            throw new ValidationException("Файл не прошёл валидацию!");
        }
        if (database.containsKey(fileRequest.getId())) {
            throw new DataAlreadyExistsException(String.format("Файл с идентификатором: [%s], уже добавлен в систему!", fileRequest.getId()));
        }
        database.put(fileRequest.getId(), new File(fileRequest));
        return database.get(fileRequest.getId());
    }

    @Override
    public File getFile(String id) {
        return database.get(id);
    }

    @Override
    public List<File> getFiles() {
        return database.values().stream().toList();
    }
}
