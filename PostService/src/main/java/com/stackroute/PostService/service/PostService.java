package com.stackroute.PostService.service;

import com.stackroute.PostService.exception.CircleDoesNotExistsException;
import com.stackroute.PostService.exception.NoSuchUserExistsException;
import com.stackroute.PostService.exception.PostAlreadyExistsException;
import com.stackroute.PostService.exception.PostDoesNotExistsException;
import com.stackroute.PostService.model.Post;

import java.util.List;

public interface PostService {
    public Post createPost(Post post) throws PostAlreadyExistsException, NoSuchUserExistsException;
    public boolean deletePost(String userId, String postId) throws PostDoesNotExistsException;
    public Post updatePost(Post post, String userId, String postId) throws NoSuchUserExistsException, PostDoesNotExistsException;
    public List<Post> getAllPosts();
//    public List<Post> getAllPostsWithSpecificCircleId(String circleId) throws CircleDoesNotExistsException;
}
