package com.stackroute.CircleService.repository;

import com.stackroute.CircleService.model.CircleUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CircleRepository extends MongoRepository<CircleUser,String>  {
    @Query(value = "{ 'circles': { $elemMatch: { 'circleId' : ?0 } }}")
    List<CircleUser> findUserByCircle(String id);
}
