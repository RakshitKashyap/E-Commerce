package com.example.userService.userService.models.dto.requestDto;

import com.example.userService.userService.utils.UserRoles;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserRegistrationRequestDto {

    @NotNull
    private String email;
    @NotNull
    private String firstName;
    private String lastName;
    @NotNull
    private String mobile;
    private String photoUrl;
    private LocalDate dob;
    private String address;
    private String city;
    private String state;
    private String pinCode;
    private String country;

    @NotNull
    private String password;

    private Set<UserRoles> roles;

}
