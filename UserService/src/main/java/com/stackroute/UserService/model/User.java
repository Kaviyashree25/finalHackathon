package com.stackroute.UserService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public class User {
    @Id
    private String userId;
    private String userName;
    private String dob;
    private String userRole;
    private String gender;

    public User() {
    }

    public User(String userId, String userName, String dob, String userRole, String gender) {
        this.userId = userId;
        this.userName = userName;
        this.dob = dob;
        this.userRole = userRole;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", dob='" + dob + '\'' +
                ", userRole='" + userRole + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
