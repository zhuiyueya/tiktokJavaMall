package com.chasemoon.gomall.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: chasemoon
 * @CreateTime: 2025-02-23
 * @Description:
 * @Version:
 */

@Configuration
public class AlipayConfig {
    @Value("${alipay.app-id}")
    private String appId;

    @Value("${alipay.gateway}")
    private String gateway;

    @Value("${alipay.merchant-private-key}")
    private String merchantPrivateKey;

    @Value("${alipay.alipay-public-key}")
    private String alipayPublicKey;

    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(gateway,appId,merchantPrivateKey,"json","UTF-8",alipayPublicKey,"RSA2");

    }
}
