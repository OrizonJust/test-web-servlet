package ru.laverno.web_service.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileValidatorTest {

    private static FileValidator validator;

    @BeforeAll
    static void setUp() {
        validator = new FileValidator();
    }

    @Test
    void testFileSize() {
        Assertions.assertTrue(validator.validFileSize(1));
        Assertions.assertTrue(validator.validFileSize(100 * 1024));
        Assertions.assertFalse(validator.validFileSize(100 * 1024 + 1));
    }

    @Test
    void testFileExtension() {
        Assertions.assertTrue(validator.validFileExtension("file.txt"));
        Assertions.assertTrue(validator.validFileExtension("file.csv"));
        Assertions.assertFalse(validator.validFileExtension("file.mp4"));
        Assertions.assertFalse(validator.validFileExtension("file.doc"));
    }
}
