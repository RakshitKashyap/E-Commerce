package com.example.userService.userService.models.entity;

import com.example.userService.userService.utils.UserRoles;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table
public class User extends Audit implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    private String userUUId;

    @Column(unique = true)
    private String emailId;

    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserProfile profile;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<UserRoles> roles;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userUUId='" + userUUId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isStatus();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isStatus();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isStatus();
    }

    @Override
    public boolean isEnabled() {
        return isStatus();
    }
}
