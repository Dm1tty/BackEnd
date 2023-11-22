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
        Thread.sleep(new Random().nextInt(4000) + 1000); // Random delay between 1 to 5 seconds
    }
}