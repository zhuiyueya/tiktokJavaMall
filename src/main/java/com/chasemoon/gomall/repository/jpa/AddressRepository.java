package com.chasemoon.gomall.repository.jpa;

import com.chasemoon.gomall.pojo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: chasemoon
 * @CreateTime: 2025-02-22
 * @Description:
 * @Version:
 */

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address getByAddressId(Long addressId);

    List<Address> getAllByUserId(int userId);
}
