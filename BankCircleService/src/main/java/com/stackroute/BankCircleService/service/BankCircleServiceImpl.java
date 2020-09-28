package com.stackroute.BankCircleService.service;

import com.stackroute.BankCircleService.exception.CircleAlreadyExistsException;
import com.stackroute.BankCircleService.exception.CircleNotFoundException;
import com.stackroute.BankCircleService.exception.NotAuthorizedException;
import com.stackroute.BankCircleService.model.Circle;
import com.stackroute.BankCircleService.repository.BankCircleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankCircleServiceImpl implements BankCircleService {

    private static final String NOT_FOUND_MESSAGE = "Circle Not Found";
    private static final String ALREADY_EXISTS = "Circle Already Exists";
    private final BankCircleRepository bankCircleRepository;

    @Autowired
    public BankCircleServiceImpl(BankCircleRepository bankCircleRepository) {
        this.bankCircleRepository = bankCircleRepository;
    }

    @Override
    public Circle createCircle(Circle circle) throws CircleAlreadyExistsException {
        Optional<Circle> optionalCircle = this.bankCircleRepository.findById(circle.getCircleId());
        if (optionalCircle.isPresent()) {
            throw new CircleAlreadyExistsException(ALREADY_EXISTS);
        }
        return this.bankCircleRepository.insert(circle);
    }

    @Override
    public Circle deleteCircle(String circleId, String userId) throws CircleNotFoundException, NotAuthorizedException{
        Optional<Circle> optionalCircle = this.bankCircleRepository.findById(circleId);
        if (optionalCircle.isEmpty()) {
            throw new CircleNotFoundException(NOT_FOUND_MESSAGE);
        }
        if(userId.equals(optionalCircle.get().getCreatedBy())) {
            this.bankCircleRepository.deleteById(circleId);
            return optionalCircle.get();
        }else{
            throw new NotAuthorizedException("Not Authorized to delete, only creater can delete");
        }
    }

    @Override
    public Circle updateCircle(Circle circle, String circleId, String userId) throws CircleNotFoundException ,NotAuthorizedException{
        Optional<Circle> optionalCircle = this.bankCircleRepository.findById(circleId);
        if (optionalCircle.isEmpty()) {
            throw new CircleNotFoundException(NOT_FOUND_MESSAGE);
        }
        if(userId.equals(optionalCircle.get().getCreatedBy())) {
            this.bankCircleRepository.deleteById(circleId);
            return this.bankCircleRepository.insert(circle);
        }else{
            throw new NotAuthorizedException("Not Authorized to delete, only creater can delete");
        }
    }

    @Override
    public Circle getCircleByCircleId(String circleId) throws CircleNotFoundException {
        Optional<Circle> optionalProduct = this.bankCircleRepository.findById(circleId);
        if (optionalProduct.isEmpty()) {
            throw new CircleNotFoundException(NOT_FOUND_MESSAGE);
        }
        return optionalProduct.get();
    }

    @Override
    public List<Circle> getAllCircle() {
        return this.bankCircleRepository.findAll();
    }
}
