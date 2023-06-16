package ru.laverno.web_service.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.laverno.web_service.exception.DataAlreadyExistsException;
import ru.laverno.web_service.exception.ValidationException;
import ru.laverno.web_service.model.dto.FileRequest;
import ru.laverno.web_service.validator.FileValidator;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    private FileService service;

    @Mock
    private FileValidator validator;

    @BeforeEach
    void setUp() {
        this.service = new FileServiceImpl(this.validator);
    }

    @Test
    void mustAddNewFile() {
        final var req = createFileRequest();

        Mockito.when(validator.validFileSize(Mockito.anyInt())).thenReturn(true);
        Mockito.when(validator.validFileExtension(Mockito.anyString())).thenReturn(true);

        final var actualFile = service.uploadFile(req);

        Assertions.assertEquals(req.getId(), actualFile.getId());
        Assertions.assertEquals(req.getFileName(), actualFile.getFileName());
        Assertions.assertEquals(req.getFileSize(), actualFile.getFileSize());
    }

    @Test
    void mustThrowValidationException() {
        final var req = createFileRequest();
        req.setFileName("file.txtt");

        Mockito.when(validator.validFileSize(Mockito.anyInt())).thenReturn(true).thenReturn(false);
        Mockito.when(validator.validFileExtension(Mockito.anyString())).thenReturn(false).thenReturn(true);

        Assertions.assertThrows(ValidationException.class, () -> service.uploadFile(req));

        req.setFileName("file.txt");
        req.setFileSize(100 * 1024 + 1);

        Assertions.assertThrows(ValidationException.class, () -> service.uploadFile(req));
    }

    @Test
    void mustThrowDataAlreadyExistsException() {
        final var req = createFileRequest();

        Mockito.when(validator.validFileSize(Mockito.anyInt())).thenReturn(true);
        Mockito.when(validator.validFileExtension(Mockito.anyString())).thenReturn(true);

        service.uploadFile(req);
        Assertions.assertThrows(DataAlreadyExistsException.class, () -> service.uploadFile(req));
    }

    @Test
    void mustReturnFile() {
        final var req = createFileRequest();

        Mockito.when(validator.validFileSize(Mockito.anyInt())).thenReturn(true);
        Mockito.when(validator.validFileExtension(Mockito.anyString())).thenReturn(true);

        final var expectedFile = service.uploadFile(req);
        final var actualFile = service.getFile("1");

        Assertions.assertEquals(expectedFile.getId(), actualFile.getId());
        Assertions.assertEquals(expectedFile.getFileName(), actualFile.getFileName());
        Assertions.assertEquals(expectedFile.getFileSize(), actualFile.getFileSize());
    }

    @Test
    void mustReturnAllFiles() {
        final var req = createFileRequest();

        Mockito.when(validator.validFileSize(Mockito.anyInt())).thenReturn(true);
        Mockito.when(validator.validFileExtension(Mockito.anyString())).thenReturn(true);

        service.uploadFile(req);
        req.setId("2");
        service.uploadFile(req);

        final var actualResult = service.getFiles();

        Assertions.assertEquals(2, actualResult.size());
        Assertions.assertEquals("1", actualResult.get(0).getId());
        Assertions.assertEquals("2", actualResult.get(1).getId());
    }

    private FileRequest createFileRequest() {
        final var req = new FileRequest();
        req.setId("1");
        req.setFileName("file.txt");
        req.setFileSize(100);

        return req;
    }
}
