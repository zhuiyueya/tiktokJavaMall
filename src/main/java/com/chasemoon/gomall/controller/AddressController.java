package com.chasemoon.gomall.controller;

import com.chasemoon.gomall.common.Result;
import com.chasemoon.gomall.pojo.dto.address.AddressRequest;
import com.chasemoon.gomall.pojo.dto.address.AddressResponse;
import com.chasemoon.gomall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chasemoon
 * @CreateTime: 2025-02-22
 * @Description:
 * @Version:
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @PostMapping("/add")
    public Result<AddressResponse>addAddress(Authentication authentication, @RequestBody AddressRequest addressRequest) {
        int userId=Integer.parseInt(authentication.getPrincipal().toString());
        return Result.success(addressService.addAddress(addressRequest,userId));
    }
}
