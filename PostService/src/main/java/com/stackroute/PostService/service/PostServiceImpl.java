package com.stackroute.PostService.service;

import com.stackroute.PostService.exception.NoSuchUserExistsException;
import com.stackroute.PostService.exception.PostAlreadyExistsException;
import com.stackroute.PostService.exception.PostDoesNotExistsException;
import com.stackroute.PostService.model.Post;
import com.stackroute.PostService.model.PostUser;
import com.stackroute.PostService.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(String userId, Post post) throws PostAlreadyExistsException, NoSuchUserExistsException {
        Optional<PostUser> userOptional = postRepository.findById(userId);
        Post createdPost;
        if (userOptional.isPresent()) {
            if (userOptional.get().getPostList() != null) {
                List<Post> postList = userOptional.get().getPostList();
                boolean flag = false;
                for (Post postItem : postList) {
                    if (post.getPostId() == postItem.getPostId()) {
                        flag = true;
                    }
                }
                if (flag) {
                    throw new PostAlreadyExistsException("Post Already Exists!");
                } else {
                    userOptional.get().getPostList().add(post);
                    postRepository.deleteById(userOptional.get().getUserId());
                    postRepository.save(userOptional.get());
                    createdPost = post;
                }
            } else {
                List<Post> list = new ArrayList<>();
                list.add(post);
                userOptional.get().setPostList(list);
                this.postRepository.delete(userOptional.get());
                this.postRepository.save(userOptional.get());
                createdPost = post;
            }
            return createdPost;
        }
        PostUser postUser = new PostUser();
        postUser.setUserId(userId);
        List<Post> posts =new ArrayList<>();
        posts.add(post);
        postUser.setPostList(posts);
        postRepository.save(postUser);
        return post;
    }

    @Override
    public Post deletePost(String userId, String postId) throws PostDoesNotExistsException {
        Optional<PostUser> userOptional = postRepository.findById(userId);
        if (userOptional.isPresent()) {
            PostUser user = userOptional.get();
            if (userOptional.get().getPostList() != null) {
                Optional<Post> deletedPost = user.getPostList().stream()
                        .filter(post -> post.getPostId().equals(postId))
                        .findFirst();

                if (deletedPost.isPresent()) {
                    user.getPostList().remove(deletedPost.get());
                    postRepository.delete(user);
                    postRepository.save(user);
                    return deletedPost.get();
                }
            }
        }
        throw new PostDoesNotExistsException("Post not found");
    }

    @Override
    public Post updatePost(Post post, String userId, String postId) throws NoSuchUserExistsException, PostDoesNotExistsException {
        Optional<PostUser> userOptional = postRepository.findById(userId);
        if (userOptional.isPresent()) {
            if (userOptional.get().getPostList() != null) {
                PostUser user = userOptional.get();
                Optional<Post> postOptional = user.getPostList().stream().filter(posts -> posts.getPostId().equals(postId)).findFirst();
                if (postOptional.isPresent()) {
                    user.getPostList().remove(postOptional.get());
                    user.getPostList().add(post);
                    postRepository.deleteById(userId);
                    postRepository.save(user);
                    return post;
                } else {
                    throw new PostDoesNotExistsException("Post not found");
                }
            } else {
                throw new PostDoesNotExistsException("Post not found");
            }
        }
        throw new NoSuchUserExistsException("User Not Found.");
    }

    @Override
    public List<Post> getAllPosts() {
        List<PostUser> userList = postRepository.findAll();
        List<Post> postList = new ArrayList<>();
        for (PostUser user : userList) {
            List<Post> userPost = user.getPostList();
            for (Post post : userPost) {
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public List<Post> getAllPostsByUser(String userId) throws NoSuchUserExistsException {
        try {
            return this.postRepository.findById(userId).get().getPostList();
        } catch (Exception e) {
            throw new NoSuchUserExistsException("No user Found");
        }
    }

    @Override
    public List<Post> getAllPostsInCircle(String circleId) throws NoSuchUserExistsException {
        try {
            List<Post> returnValue=new ArrayList<>();
            List<PostUser> users = this.postRepository.findUserByCircleId(circleId);
            for (PostUser user:users) {
                returnValue.addAll(user.getPostList().stream().filter(posts -> posts.getCircleId().equals(circleId)).collect(Collectors.toList()));
            }
            return returnValue;
        } catch (Exception e) {
            throw new NoSuchUserExistsException("No user Found");
        }
    }


}
