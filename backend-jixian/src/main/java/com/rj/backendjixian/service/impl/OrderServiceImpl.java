package com.rj.backendjixian.service.impl;


import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.OrderMapper;
import com.rj.backendjixian.model.entity.OrderEntity;
import com.rj.backendjixian.model.vo.HistoryOrderVo;
import com.rj.backendjixian.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rj.backendjixian.model.entity.table.BuyerEntityTableDef.BUYER_ENTITY;
import static com.rj.backendjixian.model.entity.table.GoodEntityTableDef.GOOD_ENTITY;
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
                .select(GOOD_ENTITY.NAME, ORDER_ENTITY.BUYER_ID, GOOD_ENTITY.IMAGE, ORDER_ENTITY.STATUS)
                .from(GOOD_ORDER_ENTITY).as("go")
                .where(ORDER_ENTITY.SHOP_ID.eq(id))
                .leftJoin(GOOD_ENTITY).as("g").on(GOOD_ORDER_ENTITY.GOOD_ID.eq(GOOD_ENTITY.ID))
                .leftJoin(ORDER_ENTITY).as("o").on(GOOD_ORDER_ENTITY.ORDER_ID.eq(ORDER_ENTITY.ID));
        return mapper.selectListByQueryAs(queryWrapper, HistoryOrderVo.class,
                ordersfieldQueryBuilder -> ordersfieldQueryBuilder
                    .field(HistoryOrderVo::getBuyer)
                    .queryWrapper(HistoryOrderVo -> QueryWrapper.create()
                        .from(BUYER_ENTITY)
                        .select(BUYER_ENTITY.ALL_COLUMNS)
                        .leftJoin(ORDER_ENTITY).on(ORDER_ENTITY.BUYER_ID.eq(BUYER_ENTITY.ID))
                        .where(ORDER_ENTITY.SHOP_ID.eq(id))));
    }

    //更新order
    @Override
    public int update(OrderEntity orderEntity) {
        return mapper.update(orderEntity);
    }
}