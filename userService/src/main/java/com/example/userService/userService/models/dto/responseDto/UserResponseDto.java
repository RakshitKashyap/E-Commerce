package com.example.userService.userService.models.dto.responseDto;

import com.example.userService.userService.models.entity.UserProfile;
import com.example.userService.userService.utils.UserRoles;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
public class UserResponseDto {

    private String emailId;
    private String firstName;
    private String lastName;
    private String mobile;
    private String profilePictureUrl;
    private LocalDate dateOfBirth;
    private String address;
    private String city;
    private String state;
    private String pinCode;
    private String country;
    private Set<UserRoles> roles;

}
