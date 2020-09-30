package com.stackroute.CircleService.service;

import com.stackroute.CircleService.exception.CircleAlreadyExistsException;
import com.stackroute.CircleService.exception.CircleNotFoundException;
import com.stackroute.CircleService.exception.UserNotFoundException;
import com.stackroute.CircleService.model.Circle;
import com.stackroute.CircleService.model.CircleUser;

import java.util.List;

public interface CircleService {


    Circle leaveCircle(String circleId, String userId) throws CircleNotFoundException, UserNotFoundException;
    Circle joinCircle(Circle circle, String userId) throws CircleAlreadyExistsException;



    void sendCircleRequest(Circle circle,String userId) throws Exception;
    void acceptRequest(Circle circle,String userId) throws UserNotFoundException, CircleAlreadyExistsException;
    void rejectRequest(Circle circle,String userId) throws  UserNotFoundException;

    List<Circle> getCirclesByUserId(String userId) throws UserNotFoundException;
    List<Circle> getAllCircles();
    List<Circle> getAllCircleRequest(String userId) throws UserNotFoundException;
    List<CircleUser> getAllUserByCircleId(String circle) throws CircleNotFoundException;
}
