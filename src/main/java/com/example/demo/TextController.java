package com.example.demo;

import org.springframework.web.bind.annotation.*;


import java.util.Base64;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class TextController {

    @CrossOrigin(origins = "http://localhost:3000")

    @PostMapping("/convert")
    public ResponseBodyEmitter convertToBase64(@RequestBody String text) {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        new Thread(() -> {
            try {
                String encoded = Base64.getEncoder().encodeToString(text.getBytes());
                for (char ch : encoded.toCharArray()) {
                    emitter.send(String.valueOf(ch));
                    Thread.sleep(new Random().nextInt(4000) + 1000); // Delay
                }
                emitter.complete();
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e);
            }
        }).start();

        return emitter;
    }
}
