package com.stackroute.BankService.repository;

import com.stackroute.BankService.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends MongoRepository<Product, String> {
}