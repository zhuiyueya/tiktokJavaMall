package com.chasemoon.gomall.service;

import com.chasemoon.gomall.pojo.dto.address.AddressRequest;
import com.chasemoon.gomall.pojo.dto.address.AddressResponse;
import com.chasemoon.gomall.pojo.entity.Address;
import com.chasemoon.gomall.repository.jpa.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chasemoon
 * @CreateTime: 2025-02-22
 * @Description:
 * @Version:
 */
@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    public AddressResponse addAddress(AddressRequest addressRequest, int userId) {
        Address address = new Address();
        BeanUtils.copyProperties(addressRequest, address);
        address.setUserId(userId);
        addressRepository.save(address);
        AddressResponse addressResponse = new AddressResponse();
        BeanUtils.copyProperties(address, addressResponse);
        return addressResponse;
    }

    public List<Address> getAllAddressByUserId(int userId) {
        List<Address>addresses= addressRepository.getAllByUserId(userId);
        return addresses;
    }
}
