package com.chasemoon.gomall.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public void login() {}
    @PostMapping("/register")
    public void register() {}

}
