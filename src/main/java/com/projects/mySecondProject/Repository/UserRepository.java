package com.projects.mySecondProject.Repository;

import com.projects.mySecondProject.Entity.BlogPost;
import com.projects.mySecondProject.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends MongoRepository <User, String>{

    Optional<User>findByUsername(String userName);
}
