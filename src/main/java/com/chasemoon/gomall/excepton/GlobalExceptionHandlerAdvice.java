package com.chasemoon.gomall.excepton;

import com.chasemoon.gomall.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {
    @ExceptionHandler
    public Result handleException(Exception e, HttpServletResponse response, HttpServletRequest request) {
        return new Result(500,"error",null);
    }
}
