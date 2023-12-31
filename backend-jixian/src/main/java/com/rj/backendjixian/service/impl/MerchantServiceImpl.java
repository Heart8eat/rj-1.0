package com.rj.backendjixian.service.impl;


import cn.hutool.crypto.digest.BCrypt;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.exception.LoginException;
import com.rj.backendjixian.mapper.MerchantMapper;
import com.rj.backendjixian.model.dto.MerchantUpdateDto;
import com.rj.backendjixian.model.entity.MerchantEntity;
import com.rj.backendjixian.service.IMerchantService;
import org.springframework.stereotype.Service;

import static com.rj.backendjixian.model.entity.table.MerchantEntityTableDef.MERCHANT_ENTITY;

/**
 * 服务层实现。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, MerchantEntity> implements IMerchantService {

    @Override
    public MerchantEntity login(String name, String password) throws LoginException {
        MerchantEntity merchantEntity = mapper.selectOneByCondition(MERCHANT_ENTITY.NAME.eq(name));
        if (merchantEntity != null && BCrypt.checkpw(password, merchantEntity.getPassword())) {
            return merchantEntity;
        }
        throw new LoginException("用户名或密码错误", name);
    }


    @Override
    public boolean updatePassword(MerchantUpdateDto merchantUpdateDto) {
        // hash 新密码
        String newHashedPassword=BCrypt.hashpw(merchantUpdateDto.getNewPassword(), BCrypt.gensalt());
        return this.updateChain()
                .set(MERCHANT_ENTITY.PASSWORD, newHashedPassword)
                .where(MERCHANT_ENTITY.ID.eq(merchantUpdateDto.getId()))
                .update();
    }


}