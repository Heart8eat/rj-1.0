package com.rj.backendjixian.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@SpringBootTest
@Transactional
class MerchantServiceImplTest {
//    private final MerchantEntity merchant = MerchantEntity.builder()
//            .name("V111")
//            .password("123456")
//            .phone("1234567890").build();
//    @Autowired
//    private MerchantServiceImpl service;
//
//    @BeforeEach
//    void before() {
//        service.save(merchant);
//    }
//
//    @Test
//    void login() throws LoginException {
//
//        // 登录成功
//        Assertions.assertEquals(merchant, service.login("V111", "123456"));
//        // 密码错误
//        Assertions.assertThrows(LoginException.class, () -> service.login("V111", "11111"));
//    }
//
//    @Test
//    void updatePassword() {
//        //  修改密码
//        Assertions.assertTrue(service.updatePassword(
//                new MerchantUpdateDto("123123123", merchant.getId()
//                )));
//    }
}