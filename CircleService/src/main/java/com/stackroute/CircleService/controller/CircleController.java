package com.stackroute.CircleService.controller;

import com.stackroute.CircleService.exception.CircleAlreadyExistsException;
import com.stackroute.CircleService.exception.CircleNotFoundException;
import com.stackroute.CircleService.exception.NotAuthorizedException;
import com.stackroute.CircleService.model.Circle;
import com.stackroute.CircleService.model.Post;
import com.stackroute.CircleService.service.CircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class CircleController {

    private CircleService circleService;
    private ResponseEntity responseEntity;

    @Autowired
    public CircleController(CircleService circleService)
    {
        this.circleService = circleService;
    }

    @PostMapping("/circle")
    public ResponseEntity<?> createCircle(@RequestBody Circle circle) {
        try {
            Circle createdCircle = this.circleService.createCircle(circle);
            responseEntity = new ResponseEntity<>(createdCircle, HttpStatus.CREATED);
        } catch (CircleAlreadyExistsException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/circle/{userId}")
    public ResponseEntity<?> joinCircle(@RequestBody Circle circle, @PathVariable("userId") String userId) {
        try {
            Circle joinedCircle = this.circleService.joinCircle(circle, userId);
            responseEntity = new ResponseEntity<>(joinedCircle.getUsers(), HttpStatus.OK);
        } catch (CircleNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/circle/{circleId}/{userId}")
    public ResponseEntity<?> deleteCircle(@PathVariable("circleId") String circleId, @PathVariable("userId") String userId) {
        try {
            Boolean deletedCircle = this.circleService.deleteCircle(circleId, userId);
            responseEntity = new ResponseEntity<>(deletedCircle, HttpStatus.OK);
        } catch (NotAuthorizedException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/circle/{circleId}/{userId}")
    public ResponseEntity<?> updateCircle(@RequestBody Circle circle, @PathVariable("circleId") String circleId, @PathVariable("userId") String userId) {
        try {
            Circle updatedCircle = this.circleService.updateCircle(circle, circleId, userId);
            responseEntity = new ResponseEntity<>(updatedCircle, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/circle/{circleId}/{userId}")
    public ResponseEntity<?> leaveCircle(@PathVariable("circleId") String circleId, @PathVariable("userId") String userId) {
        try {
            Circle leftCircle = this.circleService.leaveCircle(circleId, userId);
            responseEntity = new ResponseEntity<>(leftCircle, HttpStatus.OK);
        } catch (CircleNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/circle/posts/{circleId}/{userId}")
    public ResponseEntity<?> getAllPostsFromCircle(@PathVariable("circleId") String circleId, @PathVariable("userId") String userId) {
        try {
            List<Post> postList = this.circleService.getAllPostsOfCircle(circleId, userId);
            responseEntity = new ResponseEntity<>(postList, HttpStatus.OK);
        } catch (CircleNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
