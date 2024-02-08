package com.vikas.ConnectSocial.config;

import com.vikas.ConnectSocial.model.User;
import com.vikas.ConnectSocial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsernamePwdAuthProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final AppConfig appConfig;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByEmail(username);

        if(user == null){
            throw  new UsernameNotFoundException("User not found : "+ username);
        }else {
            if(appConfig.passwordEncoder().matches(password, user.getPassword())){
                List<GrantedAuthority> authorities = new ArrayList<>();

                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            }else {
                throw new BadCredentialsException("Invalid Password");
            }
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
