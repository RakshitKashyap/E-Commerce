package com.example.userService.userService.service.impl;

import com.example.userService.userService.models.dto.requestDto.UserFilterRequestDto;
import com.example.userService.userService.models.dto.requestDto.UserRegistrationRequestDto;
import com.example.userService.userService.models.dto.responseDto.UserResponseDto;
import com.example.userService.userService.models.entity.User;
import com.example.userService.userService.models.entity.UserProfile;
import com.example.userService.userService.repository.UserRepository;
import com.example.userService.userService.service.UserService;
import com.example.userService.userService.utils.UserRoles;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final SecurityContextService securityContext;

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
        return convertToResponseDto(user);
    }

    private Object convertToResponseDto(User user) {
        UserResponseDto responseDto = UserResponseDto.builder()
                .emailId(user.getEmailId())
                .firstName(user.getProfile().getFirstName())
                .lastName(user.getProfile().getLastName())
                .mobile(user.getProfile().getMobile())
                .profilePictureUrl(user.getProfile().getProfilePictureUrl())
                .dateOfBirth(user.getProfile().getDateOfBirth())
                .address(user.getProfile().getAddress())
                .city(user.getProfile().getCity())
                .state(user.getProfile().getState())
                .pinCode(user.getProfile().getPinCode())
                .country(user.getProfile().getCountry())
                .roles(user.getRoles())
                .build();

        return responseDto;
    }

    @Override
    public Object resetPassword(String request) {
        return null;
    }

    @Override
    public Object fetchAllUsers() {
        if (!isAdmin())
            return "Logged in user is not Admin";

        List<Object> userList = userRepository.findAll().stream().filter(user->user.isStatus()).map (user->convertToResponseDto(user)).toList();
        return userList;
    }

    private Boolean isAdmin() {
        Set<String> existingRoles = securityContext.getCurrentUserRoles();
        if(!existingRoles.contains("ADMIN")){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Object fetchUserByFilter(UserFilterRequestDto filterRequest) {
        if (!isAdmin())
            return "Logged in user is not Admin";

        log.info("filter request : {}", filterRequest.toString());

        if(ObjectUtils.isEmpty(filterRequest)){
            return null;
        }

        List<User> userList = userRepository.findAll();

        log.info("original list size: {}", userList.size());

        if ( StringUtils.isNotEmpty(filterRequest.email())){
            log.info("Filter by email >>  {}", filterRequest.email());
            userList = userList.stream().filter(user->user.getEmailId().equalsIgnoreCase(filterRequest.email().trim())).collect(Collectors.toList());
        }

        if (StringUtils.isNotEmpty(filterRequest.mobile())) {
            log.info("Filter by mobile >>  {}", filterRequest.mobile());
            userList = userList.stream().filter(user->user.getProfile().getMobile().equalsIgnoreCase(filterRequest.mobile().trim())).collect(Collectors.toList());
        }

        if (StringUtils.isNotEmpty(filterRequest.firstName())) {
            log.info("Filter by first Name >>  {}", filterRequest.firstName());
            userList = userList.stream().filter(user->user.getProfile().getFirstName().equalsIgnoreCase(filterRequest.firstName().trim())).collect(Collectors.toList());
        }

        if (StringUtils.isNotEmpty(filterRequest.lastname())) {
            log.info("Filter by last Name >>  {}", filterRequest.lastname());
            userList = userList.stream().filter(user->user.getProfile().getLastName().equalsIgnoreCase(filterRequest.lastname().trim())).collect(Collectors.toList());
        }

        return userList.stream().map(us->convertToResponseDto(us)).collect(Collectors.toList());
    }

    @Override
    public Object disableUser(String userId) {

        if (!isAdmin())
            return "Logged in user is not Admin";

        log.info("disabling user {}", userId);
        if(StringUtils.isNotEmpty(userId)){
            User user = userRepository.findByUserIdAndStatus(Long.parseLong(userId.trim()), Boolean.TRUE);

            if(Objects.nonNull(user)){
                user.setStatus(Boolean.FALSE);
               // optionalUser.get().setModifiedOn(LocalDateTime.now());
//                optionalUser.get().setModifiedBy("user");
                userRepository.save(user);
                return "Disabled";
            }
            else
                return "User not present";
        }

        return "Invalid userId";
    }

    @Override
    public Object updateUserRole(String userId, List<UserRoles> roles) {

        if (!isAdmin())
            return "Logged in user is not Admin";

        Optional<User> optionalUser = userRepository.findById(Long.parseLong(userId.trim()));

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            Set<UserRoles> rolesSet = roles.stream().collect(Collectors.toSet());
            user.setRoles(rolesSet);
//            user.setModifiedBy("user");
            user.setModifiedOn(LocalDateTime.now());
            user = userRepository.save(user);

            return convertToResponseDto(user);
        }
        return "User not found";
    }
}
