package com.example.userService.userService.service.impl;

import com.example.userService.userService.models.entity.User;
import com.example.userService.userService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailId(username);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmailId())
                .password(user.getPassword())
                .authorities((GrantedAuthority) user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.name()))
                        .toList())
                .build();
    }
}
