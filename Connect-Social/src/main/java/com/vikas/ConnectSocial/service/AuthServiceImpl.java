package com.vikas.ConnectSocial.service;

import com.vikas.ConnectSocial.config.AppConfig;
import com.vikas.ConnectSocial.config.JwtProvider;
import com.vikas.ConnectSocial.dto.AuthRequest;
import com.vikas.ConnectSocial.dto.AuthResponse;
import com.vikas.ConnectSocial.dto.UserRequest;
import com.vikas.ConnectSocial.model.User;
import com.vikas.ConnectSocial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements IAuthService{

    private final UserRepository userRepository;
    private final AppConfig appConfig;

    /************* REGISTER USER ***********************/
    @Override
    public AuthResponse registerUser(UserRequest userRequest) throws Exception {

        User isExist = userRepository.findByEmail(userRequest.getEmail());

        if(isExist != null){
            throw new Exception("Email already registered");
        }

        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(appConfig.passwordEncoder().encode(userRequest.getPassword()))
                .gender(userRequest.getGender())
                .followers(new ArrayList<>())
                .following(new ArrayList<>())
                .savedPosts(new ArrayList<>())
                .build();

        User savedUser = userRepository.save(newUser);
        log.info("User {} is saved", newUser.getId());

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());

        String token = JwtProvider.generateToken(authentication);

        return new AuthResponse(token, "User registered successfully");
    }

    @Override
    public AuthResponse loginUser(AuthRequest authRequest) {

        Authentication authentication = authenticate(authRequest.getEmail(), authRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);
        return new AuthResponse(token, "Login Successful");
    }

    private Authentication authenticate(String email, String password) {

        User user = userRepository.findByEmail(email);

        if(user == null){
            throw  new UsernameNotFoundException("User not found : "+ email);
        }else {
            if(appConfig.passwordEncoder().matches(password, user.getPassword())){
                List<GrantedAuthority> authorities = new ArrayList<>();

                return new UsernamePasswordAuthenticationToken(email, password, authorities);
            }else {
                throw new BadCredentialsException("Invalid Password");
            }
        }
    }

}
