package com.stackroute.CircleService.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public class CircleUser {
    @Id
    private String userId;
    private List<Circle> circles;
    private List<Circle> circleRequests;

    public CircleUser() {
    }

    public CircleUser(String userId, List<Circle> circles, List<Circle> circleRequests) {
        this.userId = userId;
        this.circles = circles;
        this.circleRequests = circleRequests;
    }

    public List<Circle> getCircleRequests() {
        return circleRequests;
    }

    public void setCircleRequests(List<Circle> circleRequests) {
        this.circleRequests = circleRequests;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Circle> getCircles() {
        return circles;
    }

    public void setCircles(List<Circle> circles) {
        this.circles = circles;
    }

    @Override
    public String toString() {
        return "CircleUser{" +
                "userId='" + userId + '\'' +
                ", circles=" + circles +
                ", circleRequests=" + circleRequests +
                '}';
    }
}