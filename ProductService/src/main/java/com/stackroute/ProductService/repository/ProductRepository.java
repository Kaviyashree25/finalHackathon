package com.stackroute.ProductService.repository;

import com.stackroute.ProductService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<User, String> {
}
