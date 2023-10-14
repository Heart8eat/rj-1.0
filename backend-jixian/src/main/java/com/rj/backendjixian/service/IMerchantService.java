package com.rj.backendjixian.service;


import com.mybatisflex.core.service.IService;
import com.rj.backendjixian.model.entity.MerchantEntity;

/**
 * 服务层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
public interface IMerchantService extends IService<MerchantEntity> {
    MerchantEntity login(String name, String password);

    int update(MerchantEntity merchantEntity);
}