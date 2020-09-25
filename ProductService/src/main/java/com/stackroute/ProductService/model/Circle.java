package com.stackroute.ProductService.model;

import java.util.List;

public class Circle {

    private String circleId;
    private String circleName;
    private String circleDescription;
    private List<User> users;
    private List<Post> posts;
    private User createdBy;

    public Circle() {
    }

    public Circle(String circleId, String circleName, String circleDescription, List<User> users, List<Post> posts, User createdBy) {
        this.circleId = circleId;
        this.circleName = circleName;
        this.circleDescription = circleDescription;
        this.users = users;
        this.posts = posts;
        this.createdBy = createdBy;
    }

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getCircleDescription() {
        return circleDescription;
    }

    public void setCircleDescription(String circleDescription) {
        this.circleDescription = circleDescription;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "circleId='" + circleId + '\'' +
                ", circleName='" + circleName + '\'' +
                ", circleDescription='" + circleDescription + '\'' +
                ", users=" + users +
                ", posts=" + posts +
                ", createdBy=" + createdBy +
                '}';
    }
}
