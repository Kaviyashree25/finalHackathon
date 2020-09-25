package com.stackroute.BankCircleService.service;

import com.stackroute.BankCircleService.model.Circle;
import com.stackroute.BankCircleService.exception.CircleAlreadyExistsException;
import com.stackroute.BankCircleService.exception.CircleNotFoundException;


import java.util.List;


public interface BankCircleService {

    Circle addCircle(Circle product) throws CircleAlreadyExistsException;

    Boolean deleteCircle(String circleId) throws CircleNotFoundException;

    Circle updateCircle(Circle product, String circleId) throws CircleNotFoundException;

    Circle getCircleByCircleId(String circleId) throws CircleNotFoundException;

    List<Circle> getAllCircle();

}
