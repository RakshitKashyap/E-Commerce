package com.example.userService.userService.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table
@Entity
public class UserActivityLog extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long userId;
    private String action;
    private String ipAddress;
    private LocalDateTime timestamp;
    private String userAgent;

}
