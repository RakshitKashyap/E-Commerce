package com.example.userService.userService.repository;

import com.example.userService.userService.models.entity.UserActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityLogRepository extends JpaRepository<UserActivityLog, Long> {}
