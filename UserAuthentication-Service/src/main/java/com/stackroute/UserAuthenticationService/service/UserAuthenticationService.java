package com.stackroute.UserAuthenticationService.service;

import com.stackroute.UserAuthenticationService.exception.UserAlreadyExistsException;
import com.stackroute.UserAuthenticationService.exception.UserNotFoundException;
import com.stackroute.UserAuthenticationService.model.User;

public interface UserAuthenticationService {
    public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
    boolean saveUser(User user) throws UserAlreadyExistsException;
    boolean deleteUser(String userId) throws UserNotFoundException;
    User updatePassword(User user, String userId) throws UserNotFoundException;
    User getUserByUserId(String userId) throws UserNotFoundException;
}
