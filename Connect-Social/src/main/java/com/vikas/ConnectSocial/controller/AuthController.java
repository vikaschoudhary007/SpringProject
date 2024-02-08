package com.vikas.ConnectSocial.controller;

import com.vikas.ConnectSocial.dto.AuthRequest;
import com.vikas.ConnectSocial.dto.AuthResponse;
import com.vikas.ConnectSocial.dto.UserRequest;
import com.vikas.ConnectSocial.service.IAuthService;
import com.vikas.ConnectSocial.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody UserRequest userRequest) throws Exception {
        return new ResponseEntity<>(authService.registerUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest authRequest) throws Exception {
        return new ResponseEntity<>(authService.loginUser(authRequest), HttpStatus.CREATED);
    }
}
