package com.projects.mySecondProject.Controller;
import com.projects.mySecondProject.Entity.BlogPost;
import com.projects.mySecondProject.Service.BlogPostService;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/blogposts")
public class BlogPostController{
    @Autowired
    private BlogPostService blogPostService;
@PostMapping("/post")
public ResponseEntity<?> createBlogPosts(@RequestBody BlogPost blogPost) {
    try {
        BlogPost createdBlogPost = blogPostService.createBlogPost(blogPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBlogPost);
    } catch (Exception e) {
        e.printStackTrace();  // Log stack trace for better debugging
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
    }
}@GetMapping
    public List<BlogPost> getAllBlogPosts(Authentication authentication) {
        return blogPostService.getAllBlogPost();
    }

    @GetMapping("/author/{author}")
    public List<BlogPost> getBlogPostsByAuthor(@PathVariable String author) {
        return blogPostService.getBlogPostByAuthor(author);
    }
}



