package com.stackroute.BankCircleService.controller;

import com.stackroute.BankCircleService.exception.CircleAlreadyExistsException;
import com.stackroute.BankCircleService.exception.CircleNotFoundException;
import com.stackroute.BankCircleService.exception.NotAuthorizedException;
import com.stackroute.BankCircleService.model.Circle;
import com.stackroute.BankCircleService.service.BankCircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/bank/circles")
public class BankController {

    private BankCircleService bankCircleService;

    @Autowired
    public BankController(BankCircleService bankCircleService) {
        this.bankCircleService = bankCircleService;
    }

    //Product
    @GetMapping("/{id}")
    public ResponseEntity<?> getCircleByCircleId(@PathVariable("id") String circleId) {
        ResponseEntity<?> responseEntity;
        try {
            Circle savedCircle = this.bankCircleService.getCircleByCircleId(circleId);
            responseEntity = new ResponseEntity<>(savedCircle, HttpStatus.OK);
        } catch (CircleNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    //List<Product>
    @GetMapping("")
    public ResponseEntity<?> getAllCircles() {
        return new ResponseEntity<>(this.bankCircleService.getAllCircle(), HttpStatus.OK);
    }

    //Product
    @PostMapping("")
    public ResponseEntity<?> addCircle(@RequestBody Circle circle) {
        ResponseEntity<?> responseEntity;
        try {
            Circle savedCircle = this.bankCircleService.createCircle(circle);
            responseEntity = new ResponseEntity<>(savedCircle, HttpStatus.CREATED);
        } catch (CircleAlreadyExistsException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    //Boolean
    @DeleteMapping("/{userId}/{circleId}")
    public ResponseEntity<?> deleteCircle(@PathVariable("userId") String userId,@PathVariable("circleId") String circleId) {
        ResponseEntity<?> responseEntity;
        try {
            Circle savedCircle = this.bankCircleService.getCircleByCircleId(circleId);
            this.bankCircleService.deleteCircle(circleId,userId);
            responseEntity = new ResponseEntity<>(savedCircle, HttpStatus.OK);
        } catch (CircleNotFoundException | NotAuthorizedException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Product
    @PutMapping("/{userId}/{circleId}")
    public ResponseEntity<?> updateCircle(@RequestBody Circle circle,@PathVariable("userId") String userId,@PathVariable("circleId") String circleId) {
        ResponseEntity<?> responseEntity;
        try {
            Circle savedCircle = this.bankCircleService.updateCircle(circle, circleId,userId);
            responseEntity = new ResponseEntity<>(savedCircle, HttpStatus.OK);
        } catch (CircleNotFoundException |NotAuthorizedException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


}
