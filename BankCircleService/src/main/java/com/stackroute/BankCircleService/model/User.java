package com.stackroute.BankCircleService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public class User {
    private String userId;
    private String userName;
    private String dob;
    private String userRole;
    private List<Circle> circles;
    private List<Product> products;
    private List<Post> posts;

    public User() {
    }

    public User(String userId, String userName, String dob, String userRole, List<Circle> circles, List<Product> products, List<Post> posts) {
        this.userId = userId;
        this.userName = userName;
        this.dob = dob;
        this.userRole = userRole;
        this.circles = circles;
        this.products = products;
        this.posts = posts;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public List<Circle> getCircles() {
        return circles;
    }

    public void setCircles(List<Circle> circles) {
        this.circles = circles;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", dob='" + dob + '\'' +
                ", userRole='" + userRole + '\'' +
                ", circles=" + circles +
                ", products=" + products +
                ", posts=" + posts +
                '}';
    }
}
