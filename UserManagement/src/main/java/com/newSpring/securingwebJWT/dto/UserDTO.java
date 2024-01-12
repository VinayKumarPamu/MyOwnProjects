package com.newSpring.securingwebJWT.dto;

import com.newSpring.securingwebJWT.entities.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long userId;
    private String username;
    private String email;
    private String password;
    private LocalDateTime registrationDateAndTime;
    private LocalDateTime lastLogin;
    private boolean accountStatus;
    private Role role;
    private boolean isAccountExpired;
    private boolean isAccountLocked;
    private boolean isCredentialsNonExpired;
    private int failCount;
    private LocalDateTime lockReleaseDate;

}