package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.config.JwtProvider;
import com.vikas.ConnectSocial.dto.UserRequest;
import com.vikas.ConnectSocial.dto.UserResponse;
import com.vikas.ConnectSocial.exceptions.UserNotFoundException;
import com.vikas.ConnectSocial.model.User;
import com.vikas.ConnectSocial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    /************* GET ALL USERS ***********************/
    @Override
    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToUserResponse).toList();
    }

    private UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .gender(user.getGender())
                .followers(user.getFollowers())
                .following(user.getFollowing())
                .savedPosts(user.getSavedPosts())
                .build();
    }

    /************* GET USER BY ID ***********************/
    @Override
    public UserResponse getUserById(int id) {

        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            return mapToUserResponse(user);
        }else{
            throw new UserNotFoundException("User not found");
        }
    }

    /************* GET USER BY EMAIL ***********************/
    @Override
    public UserResponse getUserByEmail(String email) {

        User user = userRepository.findByEmail(email);

        return mapToUserResponse(user);
    }

    /************* FOLLOW USER
     * User1 wants to follow User2
     * ***********************/

    @Override
    public UserResponse followUser(int userId1, int userId2) {

        Optional<User> user1Optional = userRepository.findById(userId1);
        Optional<User> user2Optional = userRepository.findById(userId2);

        if (user1Optional.isPresent() && user2Optional.isPresent()) {

            User user1 = user1Optional.get();
            User user2 = user2Optional.get();

            user2.getFollowers().add(user1.getId());
            user1.getFollowing().add(user2.getId());

            userRepository.save(user1);
            userRepository.save(user2);

            return mapToUserResponse(user1);

        } else {
            throw new UserNotFoundException("User Not Found");
        }
    }

    /************* UPDATE USER ***********************/

    @Override
    public UserResponse updateUser(UserRequest userRequest, int userId) {

        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User doesn't exist");
        }

        User olduser = userOptional.get();

        if(userRequest.getFirstName() != null){
            olduser.setFirstName(userRequest.getFirstName());
        }
        if(userRequest.getLastName() != null){
            olduser.setLastName(userRequest.getLastName());
        }
        if(userRequest.getEmail() != null){
            olduser.setEmail(userRequest.getEmail());
        }
        if(userRequest.getGender() != null){
            olduser.setGender(userRequest.getGender());
        }

        User updateduser = userRepository.save(olduser);

        return mapToUserResponse(updateduser);
    }

    /************* SEARCH USER BY QUERY ***********************/
    @Override
    public List<UserResponse> searchUser(String query) {

        List<User> users = userRepository.searchUser(query);
        return users.stream().map(this::mapToUserResponse).toList();
    }

    /************* GET USER DETAILS FROM JWT TOKEN ***********************/
    @Override
    public UserResponse getUserByJwt(String jwt) {

        String email = JwtProvider.getEmailFromJwtToken(jwt);
        return getUserByEmail(email);
    }
}
