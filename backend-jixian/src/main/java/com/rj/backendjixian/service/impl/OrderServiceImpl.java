package com.rj.backendjixian.service.impl;


import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.OrderMapper;
import com.rj.backendjixian.model.entity.OrderEntity;
import com.rj.backendjixian.model.vo.HistoryOrderVo;
import com.rj.backendjixian.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rj.backendjixian.model.entity.table.BuyerAddressEntityTableDef.BUYER_ADDRESS_ENTITY;
import static com.rj.backendjixian.model.entity.table.GoodEntityTableDef.GOOD_ENTITY;
import static com.rj.backendjixian.model.entity.table.GoodImageEntityTableDef.GOOD_IMAGE_ENTITY;
import static com.rj.backendjixian.model.entity.table.GoodOrderEntityTableDef.GOOD_ORDER_ENTITY;
import static com.rj.backendjixian.model.entity.table.OrderEntityTableDef.ORDER_ENTITY;

/**
 * 服务层实现。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements IOrderService {

    @Override
    public List<HistoryOrderVo> getHistoryOrders(String id) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(GOOD_ORDER_ENTITY.ORDER_ID, GOOD_ENTITY.NAME, ORDER_ENTITY.BUYER_ADDRESS_ID,
                        ORDER_ENTITY.STATUS, GOOD_ORDER_ENTITY.SUM)
                .from(GOOD_ORDER_ENTITY).as("go")
                .where(ORDER_ENTITY.SHOP_ID.eq(id))
                .leftJoin(GOOD_ENTITY).as("g").on(GOOD_ORDER_ENTITY.GOOD_ID.eq(GOOD_ENTITY.ID))
                .leftJoin(ORDER_ENTITY).as("o").on(GOOD_ORDER_ENTITY.ORDER_ID.eq(ORDER_ENTITY.ID));
        return mapper.selectListByQueryAs(queryWrapper, HistoryOrderVo.class,
                ordersfieldQueryBuilder -> ordersfieldQueryBuilder
                        .field(HistoryOrderVo::getBuyerAddress)
                        .queryWrapper(historyOrderVo -> QueryWrapper.create()
                                .from(BUYER_ADDRESS_ENTITY)
                                .select(BUYER_ADDRESS_ENTITY.ID, BUYER_ADDRESS_ENTITY.ADDRESS, BUYER_ADDRESS_ENTITY.BUYER_ID,
                                        BUYER_ADDRESS_ENTITY.EMAIL, BUYER_ADDRESS_ENTITY.PHONE, BUYER_ADDRESS_ENTITY.RECEIVER)
                                .where(BUYER_ADDRESS_ENTITY.ID.eq(historyOrderVo.getBuyerAddressId()))),
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(HistoryOrderVo::getImage)
                        .queryWrapper(historyOrderVo ->
                                QueryWrapper.create()
                                        .from(GOOD_IMAGE_ENTITY)
                                        .select(GOOD_IMAGE_ENTITY.URL, GOOD_IMAGE_ENTITY.WIDTH, GOOD_IMAGE_ENTITY.HEIGHT)
                                        .where(GOOD_IMAGE_ENTITY.GOOD_ID.in(
                                                QueryWrapper.create()
                                                        .select(GOOD_ORDER_ENTITY.GOOD_ID)
                                                        .from(GOOD_ORDER_ENTITY)
                                                        .where(GOOD_ORDER_ENTITY.ORDER_ID.eq(historyOrderVo.getOrderId()))
                                        ))
                                        .and(GOOD_IMAGE_ENTITY.MAIN.eq(1))
                        )
        );
//                        .leftJoin(ORDER_ENTITY).on(ORDER_ENTITY.BUYER_ID.eq(BUYER_ENTITY.ID)) ));
//                        .where(ORDER_ENTITY.SHOP_ID.eq(id))));
    }

    //更新order
    @Override
    public int update(OrderEntity orderEntity) {
        return mapper.update(orderEntity);
    }


}