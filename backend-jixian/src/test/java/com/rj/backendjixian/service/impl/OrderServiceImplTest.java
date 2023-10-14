package com.rj.backendjixian.service.impl;

import com.rj.backendjixian.model.vo.HistoryOrderVo;
import com.rj.backendjixian.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    IOrderService iOrderService;

    @Test
    void Test1() {
        List<HistoryOrderVo> list = iOrderService.getHistoryOrders("630e807bdf604941b89192af2eb7396e");
        list.forEach(System.out::println);
    }
}
