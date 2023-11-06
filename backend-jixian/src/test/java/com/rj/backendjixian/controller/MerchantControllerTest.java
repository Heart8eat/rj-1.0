package com.rj.backendjixian.controller;

import com.rj.backendjixian.model.entity.MerchantEntity;
import com.rj.backendjixian.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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