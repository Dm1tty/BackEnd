package com.example.demo;

import com.example.demo.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class TextController {

    private final TextService textService;

    @Autowired
    public TextController(TextService textService) {
        this.textService = textService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/convert")
    public ResponseBodyEmitter convertToBase64(@RequestBody String text) {
        final ResponseBodyEmitter emitter = new ResponseBodyEmitter(Long.MAX_VALUE);
        final Object lock = new Object();

        new Thread(() -> {
            try {
                String encoded = textService.encodeToBase64(text);
                for (char ch : encoded.toCharArray()) {
                    textService.simulateDelay();
                    synchronized (lock) {
                        emitter.send(String.valueOf(ch));
                    }
                }
                synchronized (lock) {
                    emitter.complete();
                }
            } catch (IOException | InterruptedException e) {
                synchronized (lock) {
                    emitter.completeWithError(e);
                }
            }
        }).start();

        return emitter;
    }
}
