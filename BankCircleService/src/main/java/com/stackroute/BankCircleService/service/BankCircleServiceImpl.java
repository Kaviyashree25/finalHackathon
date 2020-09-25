package com.stackroute.BankCircleService.service;

import com.stackroute.BankCircleService.exception.CircleAlreadyExistsException;
import com.stackroute.BankCircleService.exception.CircleNotFoundException;
import com.stackroute.BankCircleService.model.Circle;
import com.stackroute.BankCircleService.repository.BankCircleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankCircleServiceImpl implements BankCircleService {

    private static final String NOT_FOUND_MESSAGE = "Product Not Found";
    private static final String ALREADY_EXISTS = "Product Already Exists";
    private final BankCircleRepository bankCircleRepository;

    @Autowired
    public BankCircleServiceImpl(BankCircleRepository bankCircleRepository) {
        this.bankCircleRepository = bankCircleRepository;
    }


    @Override
    public Circle addCircle(Circle circle) throws CircleAlreadyExistsException {
        Optional<Circle> optionalCircle = this.bankCircleRepository.findById(circle.getCircleId());
        if (optionalCircle.isPresent()) {
            throw new CircleAlreadyExistsException(ALREADY_EXISTS);
        }
        return this.bankCircleRepository.insert(circle);
    }

    @Override
    public Boolean deleteCircle(String circleId) throws CircleNotFoundException {
        Optional<Circle> optionalCircle = this.bankCircleRepository.findById(circleId);
        if (optionalCircle.isEmpty()) {
            throw new CircleNotFoundException(NOT_FOUND_MESSAGE);
        }
        this.bankCircleRepository.deleteById(circleId);
        return true;
    }

    @Override
    public Circle updateCircle(Circle circle, String circleId) throws CircleNotFoundException {
        Optional<Circle> optionalCircle = this.bankCircleRepository.findById(circleId);
        if (optionalCircle.isEmpty()) {
            throw new CircleNotFoundException(NOT_FOUND_MESSAGE);
        }
        this.bankCircleRepository.deleteById(circleId);
        return this.bankCircleRepository.insert(circle);
    }

    @Override
    public Circle getCircleByCircleId(String circleId) throws CircleNotFoundException {
        Optional<Circle> optionalCircle = this.bankCircleRepository.findById(circleId);
        if (optionalCircle.isEmpty()) {
            throw new CircleNotFoundException(NOT_FOUND_MESSAGE);
        }
        return optionalCircle.get();
    }

    @Override
    public List<Circle> getAllCircle() {
        return this.bankCircleRepository.findAll();
    }
}
