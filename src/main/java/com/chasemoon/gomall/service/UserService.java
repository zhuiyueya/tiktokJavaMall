package com.chasemoon.gomall.service;

import com.chasemoon.gomall.pojo.dto.user.LoginRequest;
import com.chasemoon.gomall.pojo.dto.user.LoginResponse;
import com.chasemoon.gomall.pojo.dto.user.RegisterRequest;
import com.chasemoon.gomall.pojo.dto.user.RegisterResponse;
import com.chasemoon.gomall.pojo.entity.User;
import com.chasemoon.gomall.repository.jpa.UserRepository;
import com.chasemoon.gomall.util.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    public RegisterResponse register(RegisterRequest registerRequest) {
        //在数据库中查找对应的邮箱是否已经被注册
        userRepository.findByEmail(registerRequest.getEmail()).ifPresent(u ->{throw new RuntimeException("该用户已存在");});

        //存储新用户信息
        User newUser=new User();
        BeanUtils.copyProperties(registerRequest,newUser);
        userRepository.save(newUser);
        //返回null，避免在注册的返回过程中泄露用户信息
        return createRegisterResponse(newUser);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user= userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new RuntimeException("用户不存在"));

        if(!user.getPassword().equals(loginRequest.getPassword())){
            throw new RuntimeException("密码错误");

        }

        return createLoginResponse(user);
    }

    //只返回token，方便后续请求，不用多次发送账号密码，只返回token，也避免了多次传输账号密码造成的安全问题
    private LoginResponse createLoginResponse(User user){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtUtil.generateToken(user.getUserId()));
        return loginResponse;
    }

    //在注册时也返回token，用户无需再次登录即可开始使用系统。
    private RegisterResponse createRegisterResponse(User user){
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setToken(jwtUtil.generateToken(user.getUserId()));
        return registerResponse;
    }

}

