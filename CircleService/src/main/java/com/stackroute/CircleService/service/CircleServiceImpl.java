package com.stackroute.CircleService.service;

import com.stackroute.CircleService.exception.CircleAlreadyExistsException;
import com.stackroute.CircleService.exception.CircleNotFoundException;
import com.stackroute.CircleService.exception.UserNotFoundException;
import com.stackroute.CircleService.model.Circle;
import com.stackroute.CircleService.model.CircleUser;
import com.stackroute.CircleService.repository.CircleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CircleServiceImpl implements CircleService {

    CircleRepository circleRepository;

    @Autowired
    public CircleServiceImpl(CircleRepository circleRepository) {
        this.circleRepository = circleRepository;
    }

    @Override
    public Circle joinCircle(Circle circle, String userId) throws CircleAlreadyExistsException {

        Optional<CircleUser> userOptional = circleRepository.findById(userId);
        if (userOptional.isPresent()) {
            CircleUser user = userOptional.get();
            if(user.getCircles()==null){
                user.setCircles(new ArrayList<>());
            }
            if(user.getCircles().contains(circle)){
                throw new CircleAlreadyExistsException("Circle Already Exists");
            }
            user.getCircles().add(circle);
            circleRepository.deleteById(userId);
            circleRepository.save(user);
            return circle;
        } else {
            CircleUser circleUser = new CircleUser();
            circleUser.setUserId(userId);
            List<Circle> circles =new ArrayList<>();
            circles.add(circle);
            circleUser.setCircles(circles);
            circleRepository.save(circleUser);
            return circle;
        }

    }

    @Override
    public Circle leaveCircle(String circleId, String userId) throws CircleNotFoundException, UserNotFoundException {
        Optional<CircleUser> userOptional = circleRepository.findById(userId);
        if (userOptional.isPresent()) {
            CircleUser user = userOptional.get();
            if (userOptional.get().getCircles() != null) {
                Optional<Circle> circle = user.getCircles().stream().filter(circles -> circles.getCircleId().equals(circleId)).findFirst();
                if (circle.isPresent()) {
                    user.getCircles().remove(circle.get());
                } else {
                    throw new CircleNotFoundException("Circle not found");
                }
                circleRepository.delete(user);
                if(!user.getCircles().isEmpty()) {
                    circleRepository.save(user);
                }
                return circle.get();
            } else {
                //No circle present for the user, hence nothing to be left
                throw new CircleNotFoundException("Circle not found");
            }
        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public void sendCircleRequest(Circle circle, String userId){
        Optional<CircleUser> userOptional = circleRepository.findById(userId);
        if (userOptional.isPresent()) {
            userOptional.get().getCircleRequests().add(circle);
            this.circleRepository.deleteById(userId);
            this.circleRepository.insert(userOptional.get());
        } else {
            CircleUser circleUser = new CircleUser();
            circleUser.setUserId(userId);
            List<Circle> circles =new ArrayList<>();
            circles.add(circle);
            circleUser.setCircleRequests(circles);
            circleRepository.save(circleUser);
        }
    }

    @Override
    public void acceptRequest(Circle circle, String userId) throws UserNotFoundException, CircleAlreadyExistsException {
        this.joinCircle(circle, userId);
        Optional<CircleUser> userOptional = this.circleRepository.findById(userId);
        if (userOptional.isPresent()) {
            CircleUser user = userOptional.get();
            List<Circle> circles = user.getCircles();
            circles.remove(circle);
            user.setCircleRequests(circles);
            this.circleRepository.deleteById(userId);
            this.circleRepository.insert(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public void rejectRequest(Circle circle, String userId) throws UserNotFoundException {
        Optional<CircleUser> userOptional = this.circleRepository.findById(userId);
        if (userOptional.isPresent()) {
            CircleUser user = userOptional.get();
            List<Circle> circles = user.getCircles();
            circles.remove(circle);
            user.setCircleRequests(circles);
            this.circleRepository.deleteById(userId);
            this.circleRepository.insert(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public List<Circle> getCirclesByUserId(String userId) throws UserNotFoundException {
        Optional<CircleUser> optionalUser = this.circleRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return optionalUser.get().getCircles();
    }

    @Override
    public List<Circle> getAllCircles() {
        List<CircleUser> circleUsers = this.circleRepository.findAll();
        List<Circle> circles = new ArrayList<>();
        for (CircleUser circleUser : circleUsers
        ) {
            circles.addAll(circleUser.getCircles());
        }
        return circles;
    }

    @Override
    public List<Circle> getAllCircleRequest(String userId) throws UserNotFoundException {
        try{
            return this.circleRepository.findById(userId).get().getCircleRequests();
        }catch (Exception e){
            throw new UserNotFoundException("User Not Found");
        }
    }
    @Override
    public List<CircleUser> getAllUserByCircleId(String circleId){
        return this.circleRepository.findUserByCircle(circleId);
    }

}
