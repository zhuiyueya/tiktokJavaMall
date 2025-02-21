package com.chasemoon.gomall.config;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.chasemoon.gomall.common.Constants;
import com.chasemoon.gomall.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=request.getHeader(Constants.REQUEST_HEADER_AUTHORIZATION);
        log.info("token:"+token);


        if(token!=null && token.startsWith(Constants.TOKEN_PREFIX_BEARER)) {
            log.info("token bearer existed!");
            //去掉“Bearer "前缀
            token=token.substring(Constants.TOKEN_PREFIX_BEARER_LENGTH);
            log.info("token:"+token);
            try{
                String userId= jwtUtil.validateToken(token);
                log.info("userId:"+userId);
                UsernamePasswordAuthenticationToken auth =new UsernamePasswordAuthenticationToken(userId,null,new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(auth);

            }catch(RuntimeException e){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,Constants.INVALID_TOKEN);
                response.flushBuffer();   // 确保响应立即发送
                return;
            }

        }
        filterChain.doFilter(request, response);
    }
}
