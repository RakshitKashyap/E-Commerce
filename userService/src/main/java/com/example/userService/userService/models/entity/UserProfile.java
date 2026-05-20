package com.example.userService.userService.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table
@Data
public class UserProfile extends Audit{

    @Id
    private Long id; // This will be the same as User ID

    @OneToOne
    @MapsId // Shares the Primary Key with the User entity
    @JoinColumn(name = "user_id")
    private User user;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String mobile;
    private String profilePictureUrl;
    private LocalDate dateOfBirth;

    private String address;
    private String city;
    private String state;
    private String pinCode;
    private String country;

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", profilePictureUrl='" + profilePictureUrl + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
