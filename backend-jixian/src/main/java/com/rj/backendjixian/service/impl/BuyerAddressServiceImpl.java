package com.rj.backendjixian.service.impl;


import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.BuyerAddressMapper;
import com.rj.backendjixian.model.entity.BuyerAddressEntity;
import com.rj.backendjixian.service.IBuyerAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rj.backendjixian.model.entity.table.BuyerAddressEntityTableDef.BUYER_ADDRESS_ENTITY;

/**
 * 服务层实现。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Service
public class BuyerAddressServiceImpl extends ServiceImpl<BuyerAddressMapper, BuyerAddressEntity> implements IBuyerAddressService {

    /**
     * 根据buyer_id返回BuyerAddressEntity列表
     *
     * @param buyer_id
     * @return
     */
    @Override
    public List<BuyerAddressEntity> selectByBuyerId(String buyer_id) {

        return mapper.selectListByQuery(
                QueryWrapper.create().select(BUYER_ADDRESS_ENTITY.ALL_COLUMNS)
                        .from(BUYER_ADDRESS_ENTITY).where(BUYER_ADDRESS_ENTITY.BUYER_ID.eq(buyer_id)));
    }
}