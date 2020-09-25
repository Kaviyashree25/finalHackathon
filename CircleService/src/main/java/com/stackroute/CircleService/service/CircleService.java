package com.stackroute.CircleService.service;

import com.stackroute.CircleService.exception.CircleAlreadyExistsException;
import com.stackroute.CircleService.exception.CircleNotFoundException;
import com.stackroute.CircleService.exception.NotAuthorizedException;
import com.stackroute.CircleService.model.Circle;
import com.stackroute.CircleService.model.Post;

import java.util.List;

public interface CircleService {


    Circle createCircle(Circle circle) throws CircleAlreadyExistsException;

    boolean deleteCircle(String circleId, String userId) throws CircleNotFoundException, NotAuthorizedException;

    Circle joinCircle(Circle circle, String userId) throws CircleNotFoundException;

    Circle leaveCircle(String circleId, String userId) throws CircleNotFoundException;

    Circle updateCircle(Circle circle, String circleId, String userId);

    List<Post> getAllPostsOfCircle(String circleId, String userId) throws CircleNotFoundException;
}
