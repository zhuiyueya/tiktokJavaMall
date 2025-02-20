package com.chasemoon.gomall.config;

import com.chasemoon.gomall.common.Constants;
import com.chasemoon.gomall.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=request.getHeader(Constants.REQUEST_HEADER_AUTHORIZATION);
        if(token!=null && token.startsWith(Constants.TOKEN_PREFIX_BEARER)) {
            //去掉“Bearer "前缀
            token=token.substring(Constants.TOKEN_PREFIX_BEARER_LENGTH);
            try{
                String userId= JwtUtil.validateToken(token);
                UsernamePasswordAuthenticationToken auth =new UsernamePasswordAuthenticationToken(userId,null,new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }catch(RuntimeException e){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,Constants.INVALID_TOKEN);
                return;
            }

        }
        filterChain.doFilter(request, response);
    }
}
