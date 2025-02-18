package com.chasemoon.gomall.pojo.dto.user;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String confirmPassword;
}
