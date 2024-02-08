package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.dto.UserRequest;
import com.vikas.ConnectSocial.dto.UserResponse;

import java.util.List;

public interface IUserService {

    public List<UserResponse> getAllUsers();
    public UserResponse getUserById(int id);
    public UserResponse getUserByEmail(String email);
    public UserResponse followUser(int userId1, int userId2);
    public UserResponse updateUser(UserRequest userRequest, int userId);
    public List<UserResponse> searchUser(String query);
    public UserResponse getUserByJwt(String jwt);

}
