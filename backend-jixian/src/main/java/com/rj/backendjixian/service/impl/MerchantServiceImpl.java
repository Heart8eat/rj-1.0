package com.rj.backendjixian.service.impl;


import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.MerchantMapper;
import com.rj.backendjixian.model.entity.MerchantEntity;
import com.rj.backendjixian.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务层实现。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, MerchantEntity> implements IMerchantService {
    @Autowired
    MerchantMapper merchantMapper;

    @Override
    public MerchantEntity login(String name, String password) {
        MerchantEntity merchantEntity = merchantMapper.login(name, password);
        return merchantEntity;
    }

    @Override
    public int update(MerchantEntity merchantEntity) {
        return mapper.update(merchantEntity);
    }


}