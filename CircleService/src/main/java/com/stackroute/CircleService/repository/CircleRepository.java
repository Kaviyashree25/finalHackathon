package com.stackroute.CircleService.repository;

import com.stackroute.CircleService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircleRepository extends MongoRepository<User,String>  {
}
