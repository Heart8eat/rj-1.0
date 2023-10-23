package com.rj.backendjixian.OrderServiceTests;

import com.rj.backendjixian.model.dto.GoodOrderDto;
import com.rj.backendjixian.model.entity.GoodOrderEntity;
import com.rj.backendjixian.model.entity.OrderEntity;
import com.rj.backendjixian.model.vo.HistoryOrderVo;
import com.rj.backendjixian.model.vo.Response;
import com.rj.backendjixian.service.IGoodOrderService;
import com.rj.backendjixian.service.IGoodService;
import com.rj.backendjixian.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 订单服务层测试
 */
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    IOrderService orderService;
    @Autowired
    IGoodService goodService;
    @Autowired
    IGoodOrderService goodOrderService;

    String shop_id = "630e807bdf604941b89192af2eb7396e";
    GoodOrderDto goodOrderDto = new GoodOrderDto("630e807bdf604941b89192af2eb7396e","999eb2a9a68242589a504f6d24dfd5e7","7f975160dcd44768946149e52270ee54",20,200);

    /**
     * 测试查询历史订单
     */
    @Test
    void getHistoryOrdersTest(){
        List<HistoryOrderVo> list = orderService.getHistoryOrders(shop_id);
        list.forEach(System.out::println);
    }

    /**
     * 测试更改订单状态
     */
    @Test
    void updateTest(){
        OrderEntity orderEntity = orderService.getById("f05b183312f444f19c5299ee04e67eee");
        int status = 1;
        orderEntity.setStatus(status);
        System.out.println("影响的数据库记录数：  " + orderService.update(orderEntity));
    }


    /**
     *  测试新增订单
     */
    @Test
    void newOrderTest(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setStatus(0);
        orderEntity.setBuyerId(goodOrderDto.getBuyerId());
        orderEntity.setShopId(goodOrderDto.getShopId());
        orderService.save(orderEntity);

        GoodOrderEntity goodOrderEntity = new GoodOrderEntity();
        goodOrderEntity.setOrderId(orderEntity.getId());
        goodOrderEntity.setGoodId(goodOrderDto.getGoodId());
        goodOrderEntity.setQuantity(goodOrderDto.getQuantity());
        goodOrderEntity.setSum(goodOrderDto.getSum());

        goodOrderService.save(goodOrderEntity);
    }
}
