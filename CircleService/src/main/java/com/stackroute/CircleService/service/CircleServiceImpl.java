package com.stackroute.CircleService.service;

import com.stackroute.CircleService.exception.CircleAlreadyExistsException;
import com.stackroute.CircleService.exception.CircleNotFoundException;
import com.stackroute.CircleService.exception.NotAuthorizedException;
import com.stackroute.CircleService.exception.UserNotFoundException;
import com.stackroute.CircleService.model.Circle;
import com.stackroute.CircleService.model.Post;
import com.stackroute.CircleService.model.User;
import com.stackroute.CircleService.repository.CircleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CircleServiceImpl implements CircleService{

    CircleRepository circleRepository;

    @Autowired
    public CircleServiceImpl(CircleRepository circleRepository) {
        this.circleRepository = circleRepository;
    }

    @Override
    public Circle createCircle(Circle circle) throws CircleAlreadyExistsException
    {
        Circle createdCircle;
        Optional<User> userOptional = this.circleRepository.findById(circle.getCreatedBy());
        if(userOptional.get().getCircles()!=null)
        {
            if (userOptional.isPresent())
            {
                Optional<Circle> circleOptional = userOptional.get().getCircles().stream()
                        .filter(circles -> circles.getCircleId().equals(circle.getCircleId()))
                        .findFirst();
                if (circleOptional.isPresent())
                {
                    throw new CircleAlreadyExistsException("Circle Already Exists");
                }
                else {
                    userOptional.get().getCircles().add(circle);
                    this.circleRepository.delete(userOptional.get());
                    this.circleRepository.save(userOptional.get());
                    createdCircle = circle;
                }
            }
            else {
                String userId = circle.getCreatedBy();
                List<Circle> list = new ArrayList<>();
                list.add(circle);
                User user = new User();
                user.setUserId(userId);
                circle.getUsers().add(user);
                user.setCircles(list);
                this.circleRepository.save(user);
                createdCircle = circle;
            }
        }
        else {
            List<Circle> list = new ArrayList<>();
            list.add(circle);
            userOptional.get().setCircles(list);
            this.circleRepository.delete(userOptional.get());
            this.circleRepository.save(userOptional.get());
            createdCircle=circle;
        }
        return createdCircle;
    }

    @Override
    public boolean deleteCircle(String circleId, String userId) throws CircleNotFoundException, NotAuthorizedException
    {
        Optional<User> userOptional = circleRepository.findById(userId);
        User user = userOptional.get();
        boolean status = false;

        if(userOptional.get().getCircles() != null)
        {
            Optional<Circle> deletedCircle = user.getCircles().stream()
                    .filter(circle -> circle.getCircleId().equals(circleId))
                    .findFirst();

            if(deletedCircle.isPresent())
            {
                if(!deletedCircle.get().getCreatedBy().equals(userId))
                {
                    throw new NotAuthorizedException("You can't delete this circle as you are not the admin of this circle");
                }
                else {
                    user.getCircles().remove(deletedCircle.get());
                    circleRepository.delete(user);
                    circleRepository.save(user);
                    status = true;
                }
            }
            else {
                throw new CircleNotFoundException("Circle not found");
            }
        }
        else {
            //No circle present for the user, hence nothing to be deleted
            throw new CircleNotFoundException("Circle not found");
        }
        return status;
    }

    @Override
    public Circle joinCircle(Circle circle, String userId) throws CircleNotFoundException
    {
        Optional<User> userOptional = circleRepository.findById(userId);
        User user = userOptional.get();

        circle.getUsers().add(user);
        user.getCircles().add(circle);

        circleRepository.delete(user);
        circleRepository.save(user);

        return circle;
    }

    @Override
    public Circle leaveCircle(String circleId, String userId) throws CircleNotFoundException
    {
        Optional<User> userOptional = circleRepository.findById(userId);
        User user = userOptional.get();

        if(userOptional.get().getCircles() != null)
        {
            Optional<Circle> circle = user.getCircles().stream().filter(circles -> circles.getCircleId().equals(circleId)).findFirst();

            if(circle.isPresent())
            {
                user.getCircles().remove(circle.get());
                circle.get().getUsers().remove(user);
            }
            else {
                throw new CircleNotFoundException("Circle not found");
            }
            circleRepository.delete(user);
            circleRepository.save(user);
            return circle.get();
        }
        else
        {
            //No circle present for the user, hence nothing to be left
            throw new CircleNotFoundException("Circle not found");
        }
    }

    @Override
    public Circle updateCircle(Circle circle, String circleId, String userId)
    {
        Optional<User> userOptional = circleRepository.findById(userId);
        User user = userOptional.get();

        if(userOptional.get().getCircles() != null)
        {
            Optional<Circle> circleOptional = user.getCircles().stream().filter(circles -> circles.getCircleId().equals(circleId)).findFirst();
            circleOptional.get().setCircleName(circle.getCircleName());
            circleOptional.get().setCircleDescription(circle.getCircleDescription());

            user.getCircles().remove(circle);
            user.getCircles().add(circleOptional.get());

            circleRepository.save(user);
            return circleOptional.get();
        }
        else
        {
            //No circle present for the user
            return null;
        }
    }

    @Override
    public List<Post> getAllPostsOfCircle(String circleId, String userId) throws CircleNotFoundException
    {
        Optional<User> userOptional = circleRepository.findById(userId);
        User user = userOptional.get();

        if(userOptional.get().getCircles() != null)
        {
            Optional<Circle> circle = user.getCircles().stream().filter(circles -> circles.getCircleId().equals(circleId)).findFirst();
            if(circle.isEmpty())
            {
                throw new CircleNotFoundException("Circle not found");
            }
            return circle.get().getPosts();
        }
        else
        {
            //No circle present for the user
            return null;
        }
    }

    @Override
    public Boolean sendCircleRequest(Circle circle, String userId) throws UserNotFoundException {
        Optional<User> userOptional = circleRepository.findById(userId);
        if(userOptional.isPresent()){
            userOptional.get().getCircleRequests().add(circle);
            this.circleRepository.deleteById(userId);
            this.circleRepository.insert(userOptional.get());
            return true;
        }else{
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public Boolean acceptRequest(Circle circle, String userId) throws UserNotFoundException, CircleNotFoundException {
        this.joinCircle(circle,userId);
        Optional<User> userOptional=this.circleRepository.findById(userId);
        if(userOptional.isPresent()){
            userOptional.get().getCircleRequests().remove(circle);
            this.circleRepository.deleteById(userId);
            this.circleRepository.insert(userOptional.get());
            return true;
        }else{
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public Boolean rejectRequest(Circle circle, String userId) throws UserNotFoundException {
        Optional<User> userOptional=this.circleRepository.findById(userId);
        if(userOptional.isPresent()){
            userOptional.get().getCircleRequests().remove(circle);
            this.circleRepository.deleteById(userId);
            this.circleRepository.insert(userOptional.get());
            return true;
        }else{
            throw new UserNotFoundException("User not found");
        }
    }
}
