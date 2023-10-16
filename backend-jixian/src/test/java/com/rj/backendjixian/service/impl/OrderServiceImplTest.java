package com.rj.backendjixian.service.impl;

import com.rj.backendjixian.model.entity.GoodOrderEntity;
import com.rj.backendjixian.model.entity.OrderEntity;
import com.rj.backendjixian.model.vo.HistoryOrderVo;
import com.rj.backendjixian.service.IGoodOrderService;
import com.rj.backendjixian.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    IOrderService orderService;

    @Autowired
    IGoodOrderService goodOrderService;

    @Test
    void Test1() {
        List<HistoryOrderVo> list = orderService.getHistoryOrders("630e807bdf604941b89192af2eb7396e");
        list.forEach(System.out::println);
    }

    @Test
    void Test2(){
        String buyerId = "a1619e4e9a734e1c9cf1e6ba759667b9";
        String shopId = "630e807bdf604941b89192af2eb7396e";
        String goodId = "999eb2a9a68242589a504f6d24dfd5e7";
        Float sum = 100f;
        int quantity = 10;

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setStatus(0);
        orderEntity.setBuyerId(buyerId);
        orderEntity.setShopId(shopId);

        GoodOrderEntity goodOrderEntity = new GoodOrderEntity();
        goodOrderEntity.setOrderId(orderEntity.getId());
        goodOrderEntity.setGoodId(goodId);
        goodOrderEntity.setQuantity(quantity);
        goodOrderEntity.setSum(sum);

       orderService.save(orderEntity);
       goodOrderService.save(goodOrderEntity);

    }
}
