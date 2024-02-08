package com.vikas.ConnectSocial.controller;

import com.vikas.ConnectSocial.dto.ReelRequest;
import com.vikas.ConnectSocial.dto.ReelResponse;
import com.vikas.ConnectSocial.dto.UserResponse;
import com.vikas.ConnectSocial.service.IReelService;
import com.vikas.ConnectSocial.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reels")
@RequiredArgsConstructor
public class ReelController {

    private final IReelService reelService;
    private final IUserService userService;

    @PostMapping("")
    public ResponseEntity<ReelResponse> createReel(
            @RequestBody ReelRequest reelRequest,
            @RequestHeader("Authorization") String jwt){
        UserResponse signedInUser = userService.getUserByJwt(jwt);
        return new ResponseEntity<>(reelService.createReel(reelRequest, signedInUser.getId()), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<ReelResponse>> getAllReels(){
        return new ResponseEntity<>(reelService.getAllReels(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReelResponse>> getReelsByUserId(
            @PathVariable("userId") int userId){
        return new ResponseEntity<>(reelService.getReelsByUserId(userId), HttpStatus.CREATED);
    }
}
