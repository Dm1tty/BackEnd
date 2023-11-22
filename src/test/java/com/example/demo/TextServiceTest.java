package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextServiceTest {

    @Test
    void encodeToBase64_ShouldReturnCorrectEncodedValue_HelloWorld() {
        TextService service = new TextService();
        String original = "Hello, World!";
        String encoded = service.encodeToBase64(original);
        assertEquals("SGVsbG8sIFdvcmxkIQ==", encoded);
    }
    @Test
    void encodeToBase64_ShouldReturnCorrectEncodedValue_H() {
        TextService service = new TextService();
        String original = "H";
        String encoded = service.encodeToBase64(original);
        assertEquals("SA==", encoded);
    }

}