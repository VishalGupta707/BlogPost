package com.projects.mySecondProject.Repository;

import com.projects.mySecondProject.Entity.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BlogPostRepository extends MongoRepository<BlogPost,String> {
    List<BlogPost> findByAuthor(String author);
}
