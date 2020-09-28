package com.stackroute.BankCircleService.service;

import com.stackroute.BankCircleService.exception.CircleAlreadyExistsException;
import com.stackroute.BankCircleService.exception.CircleNotFoundException;
import com.stackroute.BankCircleService.exception.NotAuthorizedException;
import com.stackroute.BankCircleService.model.Circle;

import java.util.List;

public interface BankCircleService {


    Circle updateCircle(Circle circle, String circleId, String userId) throws CircleNotFoundException, NotAuthorizedException;

    Circle createCircle(Circle circle) throws CircleAlreadyExistsException;

    Circle deleteCircle(String circleId, String userId) throws CircleNotFoundException, NotAuthorizedException;
    Circle getCircleByCircleId(String circleId) throws CircleNotFoundException;
    List<Circle> getAllCircle();

}
