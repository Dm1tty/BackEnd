package com.example.demo;
import org.springframework.stereotype.Service;
import java.util.Base64;
import java.util.Random;

@Service
public class TextService {

    public String encodeToBase64(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public void simulateDelay() throws InterruptedException {
        // random delay in the range of 1 - 5 sec
        Thread.sleep(new Random().nextInt(4000) + 1000);
    }
}