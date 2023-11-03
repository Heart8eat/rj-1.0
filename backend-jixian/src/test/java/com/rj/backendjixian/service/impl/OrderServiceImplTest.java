package com.rj.backendjixian.service.impl;

import com.rj.backendjixian.model.dto.GoodOrderDto;
import com.rj.backendjixian.model.entity.GoodOrderEntity;
import com.rj.backendjixian.model.entity.OrderEntity;
import com.rj.backendjixian.model.vo.HistoryOrderVo;
import com.rj.backendjixian.service.IGoodOrderService;
import com.rj.backendjixian.service.IGoodService;
import com.rj.backendjixian.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单服务层测试
 */
@Slf4j
@SpringBootTest
@Transactional
public class OrderServiceImplTest {
    private final String shop_id = "630e807bdf604941b89192af2eb7396e";
    private final GoodOrderDto goodOrderDto = new GoodOrderDto(
            "630e807bdf604941b89192af2eb7396e", "999eb2a9a68242589a504f6d24dfd5e7",
            "7f975160dcd44768946149e52270ee54", 20, 200);
    List<HistoryOrderVo> list1 = new ArrayList<>();
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IGoodService goodService;
    @Autowired
    private IGoodOrderService goodOrderService;

    /**
     * 测试更改订单状态
     */
    @Test
    void updateTest() {
        OrderEntity orderEntity = orderService.getById("f05b183312f444f19c5299ee04e67eee");
        int status = 0;
        orderEntity.setStatus(status);
        orderService.update(orderEntity);
        Assertions.assertEquals(status, orderService.getById("f05b183312f444f19c5299ee04e67eee").getStatus());

    }


    /**
     * 测试新增订单
     */
    @Test
    void newOrderTest() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setStatus(0);
        orderEntity.setBuyerAddressId(goodOrderDto.getBuyerAddressId());
        orderEntity.setShopId(goodOrderDto.getShopId());
        orderService.save(orderEntity);

        String str = orderEntity.getId();

        GoodOrderEntity goodOrderEntity = new GoodOrderEntity();
        goodOrderEntity.setOrderId(orderEntity.getId());
        goodOrderEntity.setGoodId(goodOrderDto.getGoodId());
        goodOrderEntity.setQuantity(goodOrderDto.getQuantity());
        goodOrderEntity.setSum(goodOrderDto.getSum());

        goodOrderService.save(goodOrderEntity);

        Assertions.assertEquals(str, goodOrderEntity.getOrderId());
    }

    /**
     * 测试查询历史订单

     @Test void getHistoryOrdersTest() {
     //测试数据
     list1.add(new HistoryOrderVo("f05b183312f444f19c5299ee04e67eee", "康师傅", "0", "2.png",
     "手动支付", "e9656b856f874e838449fa88fcef60ac", new BuyerEntity("e9656b856f874e838449fa88fcef60ac",
     "test", null, "慈溪", "13819442222", null)));
     list1.add(new HistoryOrderVo("1f673d8967e342c29382d85d7a9a9a0d", "香蕉2", "0", "1.png", "手动支付",
     "7b573034c4b14fb28695b90869477366", new BuyerEntity("7b573034c4b14fb28695b90869477366",
     "test233", null, "杭州", "23219442222", null)));

     List<HistoryOrderVo> list = orderService.getHistoryOrders(shop_id);
     Assertions.assertEquals(list1, list);
     }*/

}
