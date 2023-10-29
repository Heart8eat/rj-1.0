package com.rj.backendjixian.service.impl;


import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.BuyerMapper;
import com.rj.backendjixian.model.dto.BuyerCreateDto;
import com.rj.backendjixian.model.entity.BuyerEntity;
import com.rj.backendjixian.model.vo.OrderVo;
import com.rj.backendjixian.service.IBuyerService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BuyerServiceImpl extends ServiceImpl<BuyerMapper, BuyerEntity> implements IBuyerService {

    @Override
    public List<OrderVo> getBuyerOrders(String id) {
//        QueryWrapper queryWrapper = QueryWrapper.create()
//                .from(ORDER_ENTITY)
//                .select(ORDER_ENTITY.ID, ORDER_ENTITY.CREATE_TIME, ORDER_ENTITY.FINISH_TIME, ORDER_ENTITY.STATUS)
//                .where(ORDER_ENTITY.BUYER_ID.eq(id));
//        return mapper.selectListByQueryAs(queryWrapper, OrderVo.class,
//                goodsfieldQueryBuilder -> goodsfieldQueryBuilder.
//                        field(OrderVo::getGoods).
//                        queryWrapper(orderVo -> QueryWrapper.create()
//                                .from(GOOD_ENTITY)
//                                .select(GOOD_ENTITY.ID, GOOD_ENTITY.NAME, GOOD_ENTITY.IMAGE, GOOD_ENTITY.PRICE,
//                                        GOOD_ORDER_ENTITY.QUANTITY, GOOD_ENTITY.SHOP_ID, GOOD_ENTITY.STATUS)
//                                .leftJoin(GOOD_ORDER_ENTITY).on(GOOD_ORDER_ENTITY.GOOD_ID.eq(GOOD_ENTITY.ID)).
//                                where(GOOD_ORDER_ENTITY.ORDER_ID.eq(orderVo.getId()))
//                        ),
//                totalPriceFieldQueryBuilder -> totalPriceFieldQueryBuilder.
//                        field(OrderVo::getTotalPrice)
//                        .queryWrapper(orderVo -> QueryWrapper.create()
//                                .from(GOOD_ORDER_ENTITY)
//                                .select(sum(GOOD_ORDER_ENTITY.SUM))
//                                .where(GOOD_ORDER_ENTITY.ORDER_ID.eq(orderVo.getId()))));
        return null;
    }

    @Override
    public Map<String, String> createBuyer(BuyerCreateDto buyer) {
        BuyerEntity buyerEntity = buyer.dto2Entity();
        if (mapper.insert(buyerEntity) > 0) {
            Map<String, String> map = new HashMap<>();
            map.put("id", buyerEntity.getId());
            return map;
        }
        return null;
    }
}