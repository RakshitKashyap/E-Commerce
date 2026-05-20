package com.example.userService.userService.service.impl;

import com.example.userService.userService.models.dto.requestDto.AuthenticationRequestDto;
import com.example.userService.userService.repository.UserRepository;
import com.example.userService.userService.utils.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtility jwtUtils;

    private final UserRepository userRepository;

    public String login(AuthenticationRequestDto request) {
        // This line checks the hashed password in DB against the raw password provided
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.emailOrMobile(), request.password())
        );

        // If we reach here, authentication was successful
        var user = userRepository.findByEmailId(request.emailOrMobile());
        return jwtUtils.generateToken(user.getEmailId() , user.getRoles()); // Return the JWT to the user
    }


}
