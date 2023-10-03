package com.rj.backendjixian.mapper;

import com.rj.backendjixian.entity.MerchantEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class MerchantMapperTest {
    @Autowired
    MerchantMapper mapper;
    @Test
    void insertTest(){
        MerchantEntity merchant=MerchantEntity.builder()
                .name("m3")
                .phone("1223123123")
                .password("dsaddlkasd")
                .build();
        int row=0;
        row+=mapper.insert(merchant);
        assertEquals(1,row);
    }
}