package com.rj.backendjixian.service;


import com.mybatisflex.core.service.IService;
import com.rj.backendjixian.model.entity.GoodEntity;
import com.rj.backendjixian.model.vo.GoodBriefVo;
import com.rj.backendjixian.model.vo.HistoryGoodVo;

import java.util.List;


public interface IGoodService extends IService<GoodEntity> {
    List<HistoryGoodVo> getHistoryGoodList(String shop_id);
    List<GoodBriefVo> getGoodBriefList();

    Boolean changeGoodStatue(String id, int statue);
}