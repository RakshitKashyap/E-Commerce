package com.example.userService.userService.repository;

import com.example.userService.userService.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailId(String email);

    User findByUserIdAndStatus(long parseLong, Boolean status);
}
