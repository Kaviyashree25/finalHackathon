package com.stackroute.UserAuthenticationService.service;

import com.stackroute.UserAuthenticationService.exception.UserAlreadyExistsException;
import com.stackroute.UserAuthenticationService.exception.UserNotFoundException;
import com.stackroute.UserAuthenticationService.model.User;
import com.stackroute.UserAuthenticationService.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService{
    UserAuthenticationRepository userAutheticationRepository;

    @Autowired
    public UserAuthenticationServiceImpl(UserAuthenticationRepository userAutheticationRepository) {
        this.userAutheticationRepository = userAutheticationRepository;
    }

    @Override
    public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
        User user =  userAutheticationRepository.findByUserIdAndUserPassword(userId,password);
        if(user == null){
            throw new UserNotFoundException("User Not found");
        }
        return user;
    }

    @Override
    public boolean saveUser(User user) throws UserAlreadyExistsException {
        System.out.println(user);
        Optional<User> optional =  userAutheticationRepository.findById(user.getUserId());
        boolean status;
        if(optional.isPresent()) {
            status = false;
            throw new UserAlreadyExistsException("User Already exists");
        }
        userAutheticationRepository.save(user);
        status = true;
        return status;
    }

    @Override
    public boolean deleteUser(String userId) throws UserNotFoundException {
        Optional<User> optional =  userAutheticationRepository.findById(userId);
        boolean status;
        if(optional.isEmpty()) {
            status = false;
            throw new UserNotFoundException("User Does not exist");
        }
        userAutheticationRepository.delete(optional.get());
        status = true;
        return status;
    }

    @Override
    public User updatePassword(User user, String userId) throws UserNotFoundException {
        Optional<User> optional =  userAutheticationRepository.findById(userId);
        if(optional.isEmpty()) {
            throw new UserNotFoundException("User Does not exist");
        }
        User updatedUser = optional.get();
        updatedUser.setUserPassword(user.getUserPassword());
        userAutheticationRepository.save(updatedUser);
        return updatedUser;
    }

    @Override
    public User getUserByUserId(String userId) throws UserNotFoundException {
        Optional<User> optional =  userAutheticationRepository.findById(userId);
        if(optional.isEmpty()) {
            throw new UserNotFoundException("User Does not exist");
        }
        return optional.get();
    }
}
