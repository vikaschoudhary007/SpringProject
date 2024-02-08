package com.vikas.ConnectSocial.controller;

import com.vikas.ConnectSocial.dto.PostRequest;
import com.vikas.ConnectSocial.dto.PostResponse;
import com.vikas.ConnectSocial.dto.UserResponse;
import com.vikas.ConnectSocial.service.IPostService;
import com.vikas.ConnectSocial.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final IPostService postService;
    private final IUserService userService;

    @PostMapping("")
    public ResponseEntity<PostResponse> createPost(
            @RequestHeader("Authorization") String jwt,
            @RequestBody PostRequest postRequest){
        UserResponse signedInUser = userService.getUserByJwt(jwt);
        return new ResponseEntity<>(postService.createPost(postRequest, signedInUser.getId()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(
            @RequestHeader("Authorization") String jwt,
            @PathVariable int postId) throws Exception {
        UserResponse signedInUser = userService.getUserByJwt(jwt);
        return new ResponseEntity<>(postService.deletePost(postId, signedInUser.getId()), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponse>> findPostByUserId(@PathVariable int userId){
        return new ResponseEntity<>(postService.findPostByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> findPostById(@PathVariable int postId){
        return new ResponseEntity<>(postService.findPostById(postId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PostResponse>> findAllPosts(){
        return new ResponseEntity<>(postService.findAllPosts(), HttpStatus.OK);
    }

    @PutMapping("/save/{postId}")
    public ResponseEntity<PostResponse> savePost(
            @RequestHeader("Authorization") String jwt,
            @PathVariable int postId) throws Exception {
        UserResponse signedInUser = userService.getUserByJwt(jwt);
        return new ResponseEntity<>(postService.savePost(postId, signedInUser.getId()), HttpStatus.OK);
    }

    @PutMapping("/like/{postId}")
    public ResponseEntity<PostResponse> likePost(
            @RequestHeader("Authorization") String jwt,
            @PathVariable int postId) throws Exception {
        UserResponse signedInUser = userService.getUserByJwt(jwt);
        return new ResponseEntity<>(postService.likePost(postId, signedInUser.getId()), HttpStatus.OK);
    }

}
