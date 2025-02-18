package com.chasemoon.gomall.controller;

import com.chasemoon.gomall.common.Result;
import com.chasemoon.gomall.pojo.dto.user.LoginRequest;
import com.chasemoon.gomall.pojo.dto.user.LoginResponse;
import com.chasemoon.gomall.pojo.dto.user.RegisterRequest;
import com.chasemoon.gomall.pojo.dto.user.RegisterResponse;
import com.chasemoon.gomall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {


        return Result.success(userService.login(loginRequest));
    }
    @PostMapping("/register")
    public Result<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        return Result.success(userService.register(registerRequest));
    }

}
