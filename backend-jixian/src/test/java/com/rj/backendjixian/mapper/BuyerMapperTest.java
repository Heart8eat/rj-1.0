package com.rj.backendjixian.mapper;

import com.rj.backendjixian.entity.BuyerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BuyerMapperTest {
    @Autowired
    BuyerMapper mapper;
    @Test
    void insertTest(){
        int row=0;
        BuyerEntity buyerEntity=BuyerEntity.builder().name("test233")
                .password("1233")
                .phone("23219442222")
                .address("杭州").build();
        row+=mapper.insert(buyerEntity);
        assertEquals(1,row);
    }
    @Test
    void selectTest(){
        List<BuyerEntity> list=mapper.selectAll();
        list.forEach(System.out::println);
    }


}