package com.projects.mySecondProject.Service;

import com.projects.mySecondProject.Entity.BlogPost;
import com.projects.mySecondProject.Entity.User;
import com.projects.mySecondProject.Repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;
    public BlogPost createBlogPost(BlogPost blogPost){
      if (blogPost.getTitle() == null || blogPost.getContent() == null || blogPost.getAuthor() == null) {
          throw new IllegalArgumentException("Blog post title, content and author cannot be null");
      }
      return blogPostRepository.save(blogPost);
  }

    public List<BlogPost>getAllBlogPost(){
       return blogPostRepository.findAll();
    }
    public List<BlogPost> getBlogPostByAuthor(String author){
        return blogPostRepository.findByAuthor(author);
    }
}
