package com.projects.mySecondProject.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
//@NoArgsConstructor
//@Document(collection = "BlogPost")
//
//public class BlogPost {
//    @Id
//    private String id;
//    private String title;
//    private String content;
//    //for linking user with userName
//    private String author;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "blogposts") // MongoDB collection name
public class BlogPost {
    @Id
    private String id;  // MongoDB uses String for IDs
    private String title;
    private String content;
    private String author;
}

