package com.vikas.ConnectSocial.controller;

import com.vikas.ConnectSocial.dto.UserRequest;
import com.vikas.ConnectSocial.dto.UserResponse;
import com.vikas.ConnectSocial.model.User;
import com.vikas.ConnectSocial.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") int userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable("userEmail") String userEmail){
        return new ResponseEntity<>(userService.getUserByEmail(userEmail), HttpStatus.OK);
    }

    @PutMapping("/follow/{userId2}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponse> followUser(
            @RequestHeader("Authorization") String jwt,
            @PathVariable("userId2") int userId2){

        UserResponse signedInUser = userService.getUserByJwt(jwt);
        return new ResponseEntity<>(userService.followUser(signedInUser.getId(), userId2), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUser(
            @RequestHeader("Authorization") String jwt,
            @RequestBody UserRequest userRequest){

        UserResponse signedInUser = userService.getUserByJwt(jwt);
        return new ResponseEntity<>(userService.updateUser(userRequest, signedInUser.getId()), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchUser(@RequestParam("query") String query){
        return new ResponseEntity<>(userService.searchUser(query), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getUserFromToken(@RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(userService.getUserByJwt(jwt), HttpStatus.OK);
    }
}
