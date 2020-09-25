package com.stackroute.PostService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


public class Circle {
    private String circleId;
    private List<User> users;
    private List<Post> posts;
    private User createdBy;

    public Circle() {
    }

    public Circle(String circleId, List<User> users, List<Post> posts, User createdBy) {
        this.circleId = circleId;
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
                ", users=" + users +
                ", posts=" + posts +
                ", createdBy=" + createdBy +
                '}';
    }
}
