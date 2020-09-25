package com.stackroute.PostService.controller;

import com.stackroute.PostService.exception.NoSuchUserExistsException;
import com.stackroute.PostService.exception.PostAlreadyExistsException;
import com.stackroute.PostService.exception.PostDoesNotExistsException;
import com.stackroute.PostService.model.Post;
import com.stackroute.PostService.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class PostController {

    private PostService postService;
    private ResponseEntity responseEntity;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    @ApiOperation("Create A Post")
    public ResponseEntity<?> createPost(@RequestBody Post post)
    {
        try
        {
            Post createdPost = postService.createPost(post);
            responseEntity = new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        }
        catch (PostAlreadyExistsException e)
        {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        catch (NoSuchUserExistsException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch(Exception e)
        {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/post/{userId}/{postId}")
    @ApiOperation("Delete A Post")
    public ResponseEntity<?> deletePost(@PathVariable("userId") String userId, @PathVariable("postId") String postId)
    {
        try
        {
            boolean deletedPost = postService.deletePost(userId, postId);
            responseEntity = new ResponseEntity(deletedPost, HttpStatus.OK);
        }
        catch (PostDoesNotExistsException e)
        {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch(Exception e)
        {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/post/{userId}/{postId}")
    @ApiOperation("Update A Post")
    public ResponseEntity<?> updatePost(@RequestBody Post post, @PathVariable("userId") String userId, @PathVariable("postId") String postId) {
        try
        {
            Post updatedPost = postService.updatePost(post, userId, postId);
            responseEntity = new ResponseEntity<>(updatedPost, HttpStatus.OK);
        }
        catch(PostDoesNotExistsException e)
        {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch(NoSuchUserExistsException e)
        {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/posts")
    @ApiOperation("View All Posts")
    public ResponseEntity<?> getAllPosts()
    {
        List<Post> postList = postService.getAllPosts();
        if(postList != null)
        {
            responseEntity = new ResponseEntity<>(postList, HttpStatus.OK);
        }
        else
        {
            responseEntity = new ResponseEntity("No Posts Present.", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
