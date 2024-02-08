package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.dto.AuthRequest;
import com.vikas.ConnectSocial.dto.AuthResponse;
import com.vikas.ConnectSocial.dto.UserRequest;

public interface IAuthService {
    public AuthResponse registerUser(UserRequest userRequest) throws Exception;

    public AuthResponse loginUser(AuthRequest authRequest);
}

