package com.rj.backendjixian.service.impl;


import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.GoodMapper;
import com.rj.backendjixian.model.entity.GoodEntity;
import com.rj.backendjixian.model.vo.GoodBriefVo;
import com.rj.backendjixian.model.vo.HistoryGoodVo;
import com.rj.backendjixian.service.IGoodService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rj.backendjixian.model.entity.table.GoodEntityTableDef.GOOD_ENTITY;


@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, GoodEntity> implements IGoodService {

    @Override
    public List<HistoryGoodVo> getHistoryGoodList(String shop_id) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(GOOD_ENTITY.NAME, GOOD_ENTITY.IMAGE,GOOD_ENTITY.SHELF_DATE,
                        GOOD_ENTITY.STORE,GOOD_ENTITY.TYPE,GOOD_ENTITY.VARIETY,
                        GOOD_ENTITY.WEIGHT)
                .from(GOOD_ENTITY)
                .where(GOOD_ENTITY.SHOP_ID.eq(shop_id).and(GOOD_ENTITY.STATUS.eq(0)));
        List<GoodEntity> goodEntities = mapper.selectListByQuery(queryWrapper);
        return goodEntities.stream()
                .map(HistoryGoodVo::new).toList();
    }

    @Override
    public List<GoodBriefVo> getGoodBriefList() {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(GOOD_ENTITY.ID, GOOD_ENTITY.NAME, GOOD_ENTITY.PRICE,
                        GOOD_ENTITY.IMAGE)
                .from(GOOD_ENTITY)
                .where(GOOD_ENTITY.STATUS.eq(1));
        List<GoodEntity> goodEntities = mapper.selectListByQuery(queryWrapper);
        return goodEntities.stream()
                .map(GoodBriefVo::new).toList();
    }

    @Override
    public Boolean changeGoodStatue(String id,int statue) {
        return this.updateChain()
                .set(GOOD_ENTITY.STATUS,statue)
                .where(GOOD_ENTITY.ID.eq(id))
                .update();
    }
}