package com.stackroute.ProductService.repository;

import com.stackroute.ProductService.model.ProductUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductUser, String> {
    @Query(value = "{ 'products': { $elemMatch: { 'productId' : ?0 } }}")
    List<ProductUser> findUserByProduct(String id);
}
