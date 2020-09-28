package com.stackroute.CircleService.controller;

import com.stackroute.CircleService.exception.CircleAlreadyExistsException;
import com.stackroute.CircleService.exception.CircleNotFoundException;
import com.stackroute.CircleService.exception.UserNotFoundException;
import com.stackroute.CircleService.model.Circle;
import com.stackroute.CircleService.model.CircleUser;
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
    public CircleController(CircleService circleService) {
        this.circleService = circleService;
    }


    @PostMapping("/circle/{userId}")
    public ResponseEntity<?> joinCircle(@RequestBody Circle circle, @PathVariable("userId") String userId) {
        try {
            Circle joinedCircle = this.circleService.joinCircle(circle, userId);
            responseEntity = new ResponseEntity<>(joinedCircle, HttpStatus.OK);
        } catch (CircleAlreadyExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/circle/{circleId}/{userId}")
    public ResponseEntity<?> leaveCircle(@PathVariable("circleId") String circleId, @PathVariable("userId") String userId) {
        try {
            Circle leftCircle = this.circleService.leaveCircle(circleId, userId);
            responseEntity = new ResponseEntity<>(leftCircle, HttpStatus.OK);
        } catch (CircleNotFoundException | UserNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @PostMapping("/circle/SendRequest/{userId}")
    public ResponseEntity<?> sendRequest(@RequestBody Circle circle, @PathVariable("userId") String userId) {
        try {
            this.circleService.sendCircleRequest(circle, userId);
            responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/circle/acceptRequest/{userId}")
    public ResponseEntity<?> acceptRequest(@RequestBody Circle circle, @PathVariable("userId") String userId) {
        try {
            this.circleService.acceptRequest(circle, userId);
            responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
        } catch (UserNotFoundException | CircleAlreadyExistsException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/circle/rejectRequest/{userId}")
    public ResponseEntity<?> rejectRequest(@RequestBody Circle circle, @PathVariable("userId") String userId) {
        try {
            this.circleService.rejectRequest(circle, userId);
            responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/circles/{userId}")
    public ResponseEntity<?> getCirclesByUserId(@PathVariable("userId") String userId) {
        try {
            List<Circle> circles = this.circleService.getCirclesByUserId(userId);
            responseEntity = new ResponseEntity<>(circles, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/circles")
    public ResponseEntity<?> getAllCircles() {
        try {
            List<Circle> circles = this.circleService.getAllCircles();
            responseEntity = new ResponseEntity<>(circles, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/circleRequests/{userId}")
    public ResponseEntity<?> getCircleRequests(@PathVariable("userId") String userId) {
        try {
            List<Circle> circles = this.circleService.getAllCircleRequest(userId);
            responseEntity = new ResponseEntity<>(circles, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/users/{circleId}")
    public ResponseEntity<?> getAllUsersByCircleId(@PathVariable("circleId") String circleId) {
        try {
            List<CircleUser> circles = this.circleService.getAllUserByCircleId(circleId);
            responseEntity = new ResponseEntity<>(circles, HttpStatus.OK);
        }catch (CircleNotFoundException e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
