package com.rj.backendjixian.controller;

import com.rj.backendjixian.model.entity.MerchantEntity;
import com.rj.backendjixian.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Slf4j
class GoodControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private final MerchantEntity merchant=MerchantEntity.builder()
            .id("b81a68711ee74c8cba1791f4b0f3d096")
            .name("m1")
            .password("123456")
            .phone("1234567890").build();
    @Test
    void create() throws Exception {
        String json = "{\n" +
                "  \"shopId\": \"630e807bdf604941b89192af2eb7396e\",\n" +
                "  \"price\": 10,\n" +
                "  \"name\": \"西瓜\",\n" +
                "  \"image\": [\"1.png\",\"2.png\"],\n"+
                "  \"quantity\": 10,\n" +
                "  \"type\": \"瓜\",\n" +
                "  \"variety\": \"西瓜\",\n" +
                "  \"weight\": \"1kg\",\n" +
                "  \"store\": \"室温\",\n" +
                "  \"description\": \"测试\"\n" +
                "}";
        mockMvc.perform(post("/goods/create")
                        .header("Authorization","Bearer "+ JwtUtil.createToken(merchant))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("true"));;
    }

    @Test
    void getGoodBriefList() throws Exception {
        mockMvc.perform(get("/goods/getGoodBriefList")
                        .header("Authorization","Bearer "+ JwtUtil.createToken(merchant)))
                .andDo(print())
                .andExpect(status().isOk());

    }
}