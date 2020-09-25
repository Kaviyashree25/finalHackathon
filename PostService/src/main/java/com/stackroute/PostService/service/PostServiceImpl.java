package com.stackroute.PostService.service;

import com.stackroute.PostService.exception.CircleDoesNotExistsException;
import com.stackroute.PostService.exception.NoSuchUserExistsException;
import com.stackroute.PostService.exception.PostAlreadyExistsException;
import com.stackroute.PostService.exception.PostDoesNotExistsException;
import com.stackroute.PostService.model.Circle;
import com.stackroute.PostService.model.Post;
import com.stackroute.PostService.model.User;
import com.stackroute.PostService.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService
{
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) throws PostAlreadyExistsException, NoSuchUserExistsException {
        Optional<User> userOptional = postRepository.findById(post.getUserId());
        Post createdPost;
        if(userOptional.get().getPosts() != null)
        {
            if(userOptional.isPresent())
            {
                List<Post> postList = userOptional.get().getPosts();
                boolean flag = false;
                for(Post postItem: postList)
                {
                    if(post.getPostId() == postItem.getPostId())
                    {
                        flag = true;
                    }
                }
                if(flag)
                {
                    throw new PostAlreadyExistsException("Post Already Exists!");
                }
                else
                {
                    postList.add(post);
                    postRepository.delete(userOptional.get());
                    postRepository.save(userOptional.get());
                    createdPost = post;
                }
            }
            else
            {
                throw new NoSuchUserExistsException("The user does not exists!");
            }
        }
        else {
            List<Post> list = new ArrayList<>();
            list.add(post);
            userOptional.get().setPosts(list);
            this.postRepository.delete(userOptional.get());
            this.postRepository.save(userOptional.get());
            createdPost = post;
        }
        return createdPost;
    }

    @Override
    public boolean deletePost(String userId, String postId) throws PostDoesNotExistsException
    {
        Optional<User> userOptional = postRepository.findById(userId);
        User user = userOptional.get();

        if(userOptional.get().getPosts() != null)
        {
            Optional<Post> deletedPost = user.getPosts().stream()
                    .filter(post -> post.getPostId().equals(postId))
                    .findFirst();

            if(deletedPost.isPresent())
            {
                user.getPosts().remove(deletedPost.get());
                postRepository.delete(user);
                postRepository.save(user);
                return true;
            }
            else {
                throw new PostDoesNotExistsException("Post not found");
            }
        }
        else
        {
            //No post present for the user, hence nothing to be deleted
            throw new PostDoesNotExistsException("Post not found");
        }
    }

    @Override
    public Post updatePost(Post post, String userId, String postId) throws NoSuchUserExistsException, PostDoesNotExistsException {
        Optional<User> userOptional = postRepository.findById(userId);
        if(userOptional.get().getPosts() != null)
        {
            if(userOptional.isPresent())
            {
                User user = userOptional.get();
                Optional<Post> postOptional = user.getPosts().stream().filter(posts -> posts.getPostId().equals(postId)).findFirst();
                postOptional.get().setPostContent(post.getPostContent());
                postOptional.get().setPostTitle(post.getPostTitle());

                user.getPosts().remove(post);
                user.getPosts().add(postOptional.get());

                postRepository.save(user);
                return postOptional.get();
            }
            else
            {
                throw new NoSuchUserExistsException("User Not Found.");
            }
        }
        else
        {
            //No post present for the user, hence nothing to be updated
            throw new PostDoesNotExistsException("Post not found");
        }
    }

    @Override
    public List<Post> getAllPosts() {
        List<User> userList = postRepository.findAll();
        List<Post> postList = null;
        for(User user: userList)
        {
            List<Post> userPost = user.getPosts();
            for(Post post:userPost)
            {
                postList.add(post);
            }
        }
        return postList;
    }

}
