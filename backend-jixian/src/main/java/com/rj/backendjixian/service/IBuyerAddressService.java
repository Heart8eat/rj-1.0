package com.rj.backendjixian.service;


import com.mybatisflex.core.service.IService;
import com.rj.backendjixian.model.entity.BuyerAddressEntity;

import java.util.List;

/**
 * 服务层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
public interface IBuyerAddressService extends IService<BuyerAddressEntity> {
    List<BuyerAddressEntity> selectByBuyerId(String buyer_id);
}