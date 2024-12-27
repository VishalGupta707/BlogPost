package com.projects.mySecondProject.Controller;

import com.projects.mySecondProject.Repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthCheck {
    @Autowired
    private BlogPostRepository blogPostRepository;
    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }
    @GetMapping("/test-db")
    public ResponseEntity<?> testDatabase() {
        try {
            long count = blogPostRepository.count();
            return ResponseEntity.ok("Database connection successful. Total posts: " + count);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error: " + e.getMessage());
        }
    }

}
