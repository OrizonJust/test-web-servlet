package ru.laverno.web_service.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileRequest {

    private String id;

    private int fileSize;

    private String fileName;
}
