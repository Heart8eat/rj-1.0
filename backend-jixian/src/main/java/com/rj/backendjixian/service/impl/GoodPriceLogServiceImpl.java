package com.rj.backendjixian.service.impl;


import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.GoodPriceLogMapper;
import com.rj.backendjixian.model.dto.GoodPriceLogDto;
import com.rj.backendjixian.model.entity.GoodPriceLogEntity;
import com.rj.backendjixian.model.vo.GoodPriceLogVo;
import com.rj.backendjixian.service.GoodPriceLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.rj.backendjixian.model.entity.table.GoodPriceLogEntityTableDef.GOOD_PRICE_LOG_ENTITY;

/**
 * 服务层实现。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Service
public class GoodPriceLogServiceImpl extends ServiceImpl<GoodPriceLogMapper, GoodPriceLogEntity> implements GoodPriceLogService {

    @Override
    @Transactional
    public Boolean saveLog(GoodPriceLogDto goodPriceLogDto) {
        // 插入前判断更改的价格和最新的价格是否相同
        QueryWrapper queryWrapper = QueryWrapper.create()
                .from(GOOD_PRICE_LOG_ENTITY)
                .select(GOOD_PRICE_LOG_ENTITY.PRICE, GOOD_PRICE_LOG_ENTITY.CREATE_TIME)
                .where(GOOD_PRICE_LOG_ENTITY.GOOD_ID.eq(goodPriceLogDto.getGoodId()))
                .orderBy(GOOD_PRICE_LOG_ENTITY.CREATE_TIME, false)
                .limit(1);
        GoodPriceLogEntity goodPriceLog = mapper.selectOneByQuery(queryWrapper);
        if (goodPriceLog.getPrice().equals(goodPriceLogDto.getPrice())) {
            throw new RuntimeException("和最新价格相同，无需修改");
        }

        GoodPriceLogEntity goodPriceLog2 = GoodPriceLogEntity
                .builder()
                .goodId(goodPriceLogDto.getGoodId())
                .price(goodPriceLogDto.getPrice())
                .build();
        return this.save(goodPriceLog2);
    }

    @Override
    @Transactional
    public List<GoodPriceLogVo> getGoodPriceLogVoList(String good_id) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .from(GOOD_PRICE_LOG_ENTITY)
                .select(GOOD_PRICE_LOG_ENTITY.PRICE, GOOD_PRICE_LOG_ENTITY.CREATE_TIME)
                .where(GOOD_PRICE_LOG_ENTITY.GOOD_ID.eq(good_id));

        return mapper.selectListByQueryAs(queryWrapper, GoodPriceLogVo.class);
    }
}