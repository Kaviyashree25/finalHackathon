package com.stackroute.PostService.repository;

import com.stackroute.PostService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<User, String> {
}
