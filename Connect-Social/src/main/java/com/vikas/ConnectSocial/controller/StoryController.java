package com.vikas.ConnectSocial.controller;

import com.vikas.ConnectSocial.dto.StoryRequest;
import com.vikas.ConnectSocial.dto.StoryResponse;
import com.vikas.ConnectSocial.dto.UserResponse;
import com.vikas.ConnectSocial.service.IStoryService;
import com.vikas.ConnectSocial.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/story")
@RequiredArgsConstructor
public class StoryController {

    private final IStoryService storyService;
    private final IUserService userService;

    @PostMapping("")
    public ResponseEntity<StoryResponse> createStory(@RequestBody StoryRequest storyRequest,
                                                    @RequestHeader("Authorization") String jwt){
        UserResponse signedInUser = userService.getUserByJwt(jwt);
        return new ResponseEntity<>(storyService.createStory(storyRequest, signedInUser.getId()), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StoryResponse>> getStoryByUserId(@PathVariable("userId") int userId){
        return new ResponseEntity<>(storyService.getStoryByUserId(userId), HttpStatus.OK);
    }
}
