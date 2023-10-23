package com.rj.backendjixian.service.impl;
import com.rj.backendjixian.exception.LoginException;
import com.rj.backendjixian.model.dto.MerchantUpdateDto;
import com.rj.backendjixian.model.entity.MerchantEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@SpringBootTest
@Transactional
class MerchantServiceImplTest {
    @Autowired
    private MerchantServiceImpl service;

    private final MerchantEntity merchant=MerchantEntity.builder()
            .id("b81a68711ee74c8cba1791f4b0f3d096")
            .name("m1")
            .password("123456")
            .phone("1234567890").build();


    @Test
    void login() throws LoginException {
        // 登录成功
        Assertions.assertEquals(merchant,service.login("m1","123456"));
        // 密码错误
        Assertions.assertThrows(LoginException.class,()->service.login("m1","1238"));
    }

    @Test
    void updatePassword() {
        //  修改密码
        Assertions.assertTrue(service.updatePassword(
                new MerchantUpdateDto("123458","b81a68711ee74c8cba1791f4b0f3d096"
                )));
    }
}