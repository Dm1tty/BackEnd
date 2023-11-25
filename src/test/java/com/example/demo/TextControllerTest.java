package com.example.demo;

import com.example.demo.TextService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.junit.jupiter.api.Assertions.assertTrue;
@WebMvcTest(TextController.class)
class TextControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TextService textService;


    @Test
    void convertToBase64_ShouldProcessRequest_HelloWorld() throws Exception {
        String original = "Hello, World!";
        String encoded = "SGVsbG8sIFdvcmxkIQ==";

        given(textService.encodeToBase64(original)).willReturn(encoded);
        mockMvc.perform(post("/api/convert")
                        .content(original)
                        .contentType("text/plain"))
                .andExpect(status().isOk());

    }

    @Test
    void convertToBase64_ShouldProcessRequest_Error() throws Exception {
        String original = "";
        String encoded = "";

        given(textService.encodeToBase64(original)).willReturn(encoded);
        mockMvc.perform(post("/api/convert")
                        .content(original)
                        .contentType("text/plain"))
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void whenSimulateDelay_thenShouldTakeOneToFiveSeconds() throws InterruptedException {
        TextService service = new TextService();

        for (int i = 0; i < 3; i++) {
            long start = System.currentTimeMillis();
            service.simulateDelay();
            long end = System.currentTimeMillis();

            long duration = end - start;
            assertTrue(duration >= 1000 && duration <= 5000);
        }
    }

}