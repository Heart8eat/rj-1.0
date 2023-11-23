package com.rj.backendjixian.service.impl;


import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.GoodTypeMapper;
import com.rj.backendjixian.model.entity.GoodTypeEntity;
import com.rj.backendjixian.service.IGoodTypeService;
import org.springframework.stereotype.Service;

import static com.rj.backendjixian.model.entity.table.GoodTypeEntityTableDef.GOOD_TYPE_ENTITY;

/**
 * 服务层实现。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Service
public class GoodTypeServiceImpl extends ServiceImpl<GoodTypeMapper, GoodTypeEntity> implements IGoodTypeService {



    @Override
    public String getTypeIdOrCreate(String typeName) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .from(GOOD_TYPE_ENTITY)
                .select(GOOD_TYPE_ENTITY.ID)
                .where(GOOD_TYPE_ENTITY.TYPE_NAME.eq(typeName));
        String typeId=this.getObjAs(queryWrapper, String.class);
        // 如果是新类别则向表中插入数据
        if(typeId==null){
            GoodTypeEntity goodTypeEntity=GoodTypeEntity
                    .builder()
                    .typeName(typeName)
                    .build();
            this.save(goodTypeEntity);
            typeId= goodTypeEntity.getId();
        }
        return typeId;
    }
}