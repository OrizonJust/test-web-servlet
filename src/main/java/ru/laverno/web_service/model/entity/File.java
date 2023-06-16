package ru.laverno.web_service.model.entity;

import lombok.Getter;
import ru.laverno.web_service.model.dto.FileRequest;

//Что-то потипу энтити
@Getter
public class File {

    private String id;

    private String fileName;

    private int fileSize;

    public File(FileRequest req) {
        this.id = req.getId();
        this.fileSize = req.getFileSize();
        this.fileName = req.getFileName();
    }
}
