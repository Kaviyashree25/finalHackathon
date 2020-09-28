package com.stackroute.BankCircleService.repository;

import com.stackroute.BankCircleService.model.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCircleRepository extends MongoRepository<Circle, String> {
}