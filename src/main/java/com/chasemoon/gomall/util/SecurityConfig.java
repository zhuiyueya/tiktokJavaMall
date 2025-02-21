package com.chasemoon.gomall.util;


import com.chasemoon.gomall.config.JwtAuthenticationFilter;
import com.chasemoon.gomall.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /*
     * @Description:
     * @param null
     * @return:
     * @Author:  34362
     * @date:  2025/2/20 11:01
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())//关闭csrf
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/login", "/user/register","/product/list").permitAll()//对外公开的接口
                        //.requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyRequest().authenticated()
                )//其他接口需要验证

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class//在用户名密码过滤器之前先执行token过滤器
                );

        return http.build();
    }
}