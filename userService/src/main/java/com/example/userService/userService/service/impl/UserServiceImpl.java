package com.example.userService.userService.service.impl;

import com.example.userService.userService.models.dto.requestDto.AuthenticationRequestDto;
import com.example.userService.userService.models.dto.requestDto.UserRegistrationRequestDto;
import com.example.userService.userService.models.entity.User;
import com.example.userService.userService.models.entity.UserProfile;
import com.example.userService.userService.repository.UserRepository;
import com.example.userService.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Object registerNewUser(UserRegistrationRequestDto requestDto) {
        log.info("Inside registerNewUser with request :{}", requestDto);
        User user = new User();

        user.setUserUUId(UUID.randomUUID().toString());
        user.setEmailId(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setRoles(requestDto.getRoles());
        user.setStatus(Boolean.TRUE);
        user.setCreatedBy(requestDto.getEmail());
        user.setModifiedBy(requestDto.getEmail());
        user.setCreatedOn(LocalDateTime.now());
        user.setModifiedOn(LocalDateTime.now());

        UserProfile profile = new UserProfile();
        profile.setFirstName(requestDto.getFirstName());
        profile.setLastName(requestDto.getLastName());
        profile.setMobile(requestDto.getMobile());
        profile.setProfilePictureUrl(requestDto.getPhotoUrl());
        profile.setDateOfBirth(requestDto.getDob());
        profile.setAddress(requestDto.getAddress());
        profile.setCity(requestDto.getCity());
        profile.setState(requestDto.getState());
        profile.setPinCode(requestDto.getPinCode());
        profile.setCountry(requestDto.getCountry());
        profile.setStatus(Boolean.TRUE);
        profile.setCreatedBy(requestDto.getEmail());
        profile.setModifiedBy(requestDto.getEmail());
        profile.setCreatedOn(LocalDateTime.now());
        profile.setModifiedOn(LocalDateTime.now());

        profile.setUser(user);
        user.setProfile(profile);
        log.info("profile to be saved {}", profile);
        log.info("user to be saved {}", user);
        userRepository.save(user);
        return user.toString();
    }

    @Override
    public Object userLogin(AuthenticationRequestDto requestDto) {
        return null;
    }

    @Override
    public Object resetPassword(Object request) {
        return null;
    }
}
