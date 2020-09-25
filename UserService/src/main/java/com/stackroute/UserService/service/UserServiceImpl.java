package com.stackroute.UserService.service;

import com.stackroute.UserService.exception.UserAlreadyExistsException;
import com.stackroute.UserService.exception.UserNotFoundException;
import com.stackroute.UserService.model.User;
import com.stackroute.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;


    private static final String NOT_FOUND_MESSAGE = "Product Not Found";
    private static final String ALREADY_EXISTS = "Product Already Exists";

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        Optional<User> optionalProduct = this.userRepository.findById(user.getUserId());
        if (optionalProduct.isPresent()) {
            throw new UserAlreadyExistsException(ALREADY_EXISTS);
        }
        return this.userRepository.insert(user);
    }

    @Override
    public User deleteUser(String userId) throws UserNotFoundException {
        Optional<User> optionalProduct = this.userRepository.findById(userId);
        if (optionalProduct.isEmpty()) {
            throw new UserNotFoundException(NOT_FOUND_MESSAGE);
        }
        this.userRepository.deleteById(userId);
        return optionalProduct.get();
    }

    @Override
    public User updateUser(User user,String userId) throws UserNotFoundException {
        Optional<User> optionalProduct = this.userRepository.findById(userId);
        if (optionalProduct.isEmpty()) {
            throw new UserNotFoundException(NOT_FOUND_MESSAGE);
        }
        this.userRepository.deleteById(userId);
        return this.userRepository.insert(user);
    }
}
