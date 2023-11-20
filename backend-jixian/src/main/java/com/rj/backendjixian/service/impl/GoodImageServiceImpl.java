package com.rj.backendjixian.service.impl;


import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.GoodImageMapper;
import com.rj.backendjixian.model.entity.GoodImageEntity;
import com.rj.backendjixian.service.IGoodImageService;
import org.springframework.stereotype.Service;

import static com.rj.backendjixian.model.entity.table.GoodImageEntityTableDef.GOOD_IMAGE_ENTITY;

/**
 * 服务层实现。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Service
public class GoodImageServiceImpl extends ServiceImpl<GoodImageMapper, GoodImageEntity> implements IGoodImageService {
    /**
     * @param fakeId 假id
     * @param goodId 商品id
     * @return false 商品id绑定失败 true 商品id绑定成功
     * */
    @Override
    public boolean bindGoodId(String fakeId, String goodId) {
        return this.updateChain()
                .from(GOOD_IMAGE_ENTITY)
                .set(GOOD_IMAGE_ENTITY.GOOD_ID, goodId)
                .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(fakeId))
                .update();
    }
    @Override
    public boolean deleteAllGoodImageByGoodId(String goodId){
        return this.remove(GOOD_IMAGE_ENTITY.GOOD_ID.eq(goodId));
    }
}