package com.rj.backendjixian.service.impl;


import cn.hutool.crypto.digest.BCrypt;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.exception.LoginException;
import com.rj.backendjixian.mapper.BuyerMapper;
import com.rj.backendjixian.model.dto.BuyerCreateDto;
import com.rj.backendjixian.model.entity.BuyerEntity;
import com.rj.backendjixian.model.vo.OrderVo;
import com.rj.backendjixian.service.IBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mybatisflex.core.query.QueryMethods.select;
import static com.mybatisflex.core.query.QueryMethods.sum;
import static com.rj.backendjixian.model.entity.table.BuyerAddressEntityTableDef.BUYER_ADDRESS_ENTITY;
import static com.rj.backendjixian.model.entity.table.BuyerEntityTableDef.BUYER_ENTITY;
import static com.rj.backendjixian.model.entity.table.GoodEntityTableDef.GOOD_ENTITY;
import static com.rj.backendjixian.model.entity.table.GoodOrderEntityTableDef.GOOD_ORDER_ENTITY;
import static com.rj.backendjixian.model.entity.table.OrderEntityTableDef.ORDER_ENTITY;


@Service
public class BuyerServiceImpl extends ServiceImpl<BuyerMapper, BuyerEntity> implements IBuyerService {
    @Autowired
    private GoodServiceImpl goodService;
    @Override
    public List<OrderVo> getBuyerOrders(String buyId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .from(ORDER_ENTITY)
                .select(ORDER_ENTITY.DEFAULT_COLUMNS)
                .join(BUYER_ADDRESS_ENTITY).on(ORDER_ENTITY.BUYER_ADDRESS_ID.eq(BUYER_ADDRESS_ENTITY.ID))
                .join(BUYER_ENTITY).on(BUYER_ADDRESS_ENTITY.BUYER_ID.eq(BUYER_ENTITY.ID))
                .where(BUYER_ENTITY.ID.eq(buyId));

        return mapper.selectListByQueryAs(queryWrapper, OrderVo.class,
                goodsfieldQueryBuilder -> goodsfieldQueryBuilder.
                        field(OrderVo::getGoods).
                        queryWrapper(orderVo ->
                                goodService
                                        .createGoodQueryWrapper(null,null,null,null,
                                                GOOD_ENTITY.ID.in(
                                                select(GOOD_ORDER_ENTITY.GOOD_ID)
                                                        .from(GOOD_ORDER_ENTITY)
                                                        .where(GOOD_ORDER_ENTITY.ORDER_ID.eq(orderVo.getId()))
                                        ))

                        ),
                totalPriceFieldQueryBuilder -> totalPriceFieldQueryBuilder.
                        field(OrderVo::getTotalPrice)
                        .queryWrapper(orderVo ->
                                QueryWrapper.create()
                                .from(GOOD_ORDER_ENTITY)
                                .select(sum(GOOD_ORDER_ENTITY.SUM))
                                .where(GOOD_ORDER_ENTITY.ORDER_ID.eq(orderVo.getId())))

        );

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

    @Override
    public BuyerEntity login(String name, String pwd) throws LoginException {
        BuyerEntity buyerEntity = mapper.selectOneByCondition(BUYER_ENTITY.NAME.eq(name));
        if (buyerEntity != null && BCrypt.checkpw(pwd, buyerEntity.getPassword())) {
            return buyerEntity;
        }
        throw new LoginException("用户名或密码错误", name);
    }


}