package com.rj.backendjixian.service.impl;

import com.rj.backendjixian.model.vo.OrderVo;
import com.rj.backendjixian.service.IBuyerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BuyerServiceImplTest {
    @Autowired
    IBuyerService buyerService;
    @Test
    void getBuyerOrders() {
        List<OrderVo> list=buyerService.getBuyerOrders("e9656b856f874e838449fa88fcef60ac");
        list.forEach(System.out::println);
    }
}