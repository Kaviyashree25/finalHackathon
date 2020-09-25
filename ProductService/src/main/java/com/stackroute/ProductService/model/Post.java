package com.stackroute.ProductService.model;

public class Post {

    private String postId;
    private String userId;
    private String circleId;
    private String postTitle;
    private String postContent;

    public Post() {
    }

    public Post(String postId, String userId, String circleId, String postTitle, String postContent) {
        this.postId = postId;
        this.userId = userId;
        this.circleId = circleId;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", userId='" + userId + '\'' +
                ", circleId='" + circleId + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                '}';
    }
}
