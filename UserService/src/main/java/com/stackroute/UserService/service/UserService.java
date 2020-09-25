package com.stackroute.UserService.service;


import com.stackroute.UserService.exception.UserAlreadyExistsException;
import com.stackroute.UserService.exception.UserNotFoundException;
import com.stackroute.UserService.model.User;

public interface UserService {
    public User addUser(User user) throws UserAlreadyExistsException;
    public User deleteUser(String userId) throws UserNotFoundException;
    public User updateUser(User user,String userId) throws UserNotFoundException;
}
