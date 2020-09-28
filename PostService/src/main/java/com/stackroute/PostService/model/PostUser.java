package com.stackroute.PostService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class PostUser {
    @Id
    private String userId;
    private List<Post> postList;

    public PostUser() {
    }

    public PostUser(String userId, List<Post> postList) {
        this.userId = userId;
        this.postList = postList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Post> getPostList() {
        return postList;
    }

    @Override
    public String toString() {
        return "PostUser{" +
                "userId='" + userId + '\'' +
                ", postList=" + postList +
                '}';
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
