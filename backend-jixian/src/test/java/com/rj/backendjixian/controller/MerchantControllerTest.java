package com.rj.backendjixian.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class MerchantControllerTest {

//    private final MerchantEntity merchant = MerchantEntity.builder()
//            .id("b81a68711ee74c8cba1791f4b0f3d096")
//            .name("m1")
//            .password("123456")
//            .phone("1234567890").build();
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void login() throws Exception {
//        mockMvc.perform(get("/merchants/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .queryParam("name", "m1")
//                        .queryParam("password", "123456"))
//                .andDo((res) -> {
//                    String content = res.getResponse().getContentAsString();
//                })
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.token").value(JwtUtil.createToken(merchant)))
//                .andExpect(jsonPath("$.data.type").value("Bearer"));
//    }
//
//    @Test
//    void updatePassword() throws Exception {
//        String json = "{\"newPassword\":\"11111111\"}";
//        mockMvc.perform(put("/merchants/updatePassword")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("Authorization", "Bearer " + JwtUtil.createToken(merchant))
//                        .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").value("true"));
//    }
}