package dev.nyusu.Spring6.Controller;



import dev.nyusu.Spring6.Service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// MyController.java
@RestController
@RequestMapping("/api")

public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/greet/{name}")
    public ResponseEntity<String> greet(@PathVariable String name) {
        try {
            String greeting = myService.generateGreeting(name);
            return ResponseEntity.ok(greeting);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
