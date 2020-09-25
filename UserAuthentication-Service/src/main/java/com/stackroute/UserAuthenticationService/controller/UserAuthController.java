package com.stackroute.UserAuthenticationService.controller;

import com.stackroute.UserAuthenticationService.exception.UserAlreadyExistsException;
import com.stackroute.UserAuthenticationService.exception.UserNotFoundException;
import com.stackroute.UserAuthenticationService.model.User;
import com.stackroute.UserAuthenticationService.service.UserAuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class UserAuthController {
    UserAuthenticationService userAuthenticationService;
    ResponseEntity responseEntity;
    private Map<String,String> map = new HashMap<>();

    @Autowired
    public UserAuthController(UserAuthenticationService authicationService) {
        this.userAuthenticationService = authicationService;
    }

    @PostMapping("/api/v1/auth/register")
    public ResponseEntity saveUser(@RequestBody User user) {
        try {
            boolean createdUser = userAuthenticationService.saveUser(user);
            responseEntity = new ResponseEntity(user, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity userLogin(@RequestBody User user){
        try {
            String jwtToken = getToken(user.getUserId(),user.getUserPassword());
            map.put("message" , "User successfully logged in");
            map.put("token",jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message",e.getMessage());
            map.put("token",null);
            responseEntity = new ResponseEntity(map, HttpStatus.UNAUTHORIZED);
        }
        responseEntity = new ResponseEntity(map,HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/api/v1/auth/deregister/{userId}")
    public ResponseEntity deleteUser(@PathVariable(value = "userId") String userId) {
        try {
            User deletedUser = userAuthenticationService.getUserByUserId(userId);
            boolean status = userAuthenticationService.deleteUser(userId);
            responseEntity = new ResponseEntity(deletedUser, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/api/v1/auth/user/changepassword/{userId}")
    public ResponseEntity updateUserPassword(@RequestBody User user, @PathVariable(value = "userId") String userId) {
        try {
            User updatedUser = userAuthenticationService.updatePassword(user, userId);
            responseEntity = new ResponseEntity(updatedUser, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    // Generate JWT token
    public String getToken(String username, String password) throws Exception {
        String jwtToken="";
        if(username == null || password == null){
            throw new Exception("Please send valid username or password");
        }
        User user =  userAuthenticationService.findByUserIdAndPassword(username,password);
        if(user == null){
            throw new Exception("Invalid credentials");
        }
        else {
            jwtToken = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 300000))
                    .signWith(SignatureAlgorithm.HS256,"secretkey")
                    .compact();
        }
        return jwtToken;
    }
}
