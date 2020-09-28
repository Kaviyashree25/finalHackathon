package com.stackroute.PostService.repository;


import com.stackroute.PostService.model.Post;
import com.stackroute.PostService.model.PostUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<PostUser, String> {
    @Query(value = "{ 'postList': { $elemMatch: { 'circleId' : ?0 } }}")
    List<PostUser> findUserByCircleId(String circleId);
}
