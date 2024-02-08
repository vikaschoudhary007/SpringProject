package com.vikas.ConnectSocial.controller;

import com.vikas.ConnectSocial.dto.CommentRequest;
import com.vikas.ConnectSocial.dto.CommentResponse;
import com.vikas.ConnectSocial.dto.UserResponse;
import com.vikas.ConnectSocial.service.ICommentService;
import com.vikas.ConnectSocial.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final ICommentService commentService;
    private final IUserService userService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentResponse> createComment(
            @RequestHeader("Authorization") String jwt,
            @RequestBody CommentRequest commentRequest,
            @PathVariable("postId") int postId){
        UserResponse signedInUser = userService.getUserByJwt(jwt);
        return new ResponseEntity<>(commentService.createComment(commentRequest,postId,signedInUser.getId()), HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable("commentId") int commentId) throws Exception {
        return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.OK);
    }

    @PutMapping("/like/{commentId}")
    public ResponseEntity<CommentResponse> likeComment(@PathVariable("commentId") int commentId,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        UserResponse signedInUser = userService.getUserByJwt(jwt);
        return new ResponseEntity<>(commentService.likeComment(commentId, signedInUser.getId()), HttpStatus.OK);
    }

}
