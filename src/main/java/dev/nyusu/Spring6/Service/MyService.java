package dev.nyusu.Spring6.Service;

import org.springframework.stereotype.Service;

// MyService.java
@Service
public class MyService {

    public String generateGreeting(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return "Hello, " + name + "!";
    }
}


