package com.rj.backendjixian.service;


import com.mybatisflex.core.service.IService;
import com.rj.backendjixian.model.dto.GoodPriceLogDto;
import com.rj.backendjixian.model.entity.GoodPriceLogEntity;
import com.rj.backendjixian.model.vo.GoodPriceLogVo;

import java.util.List;

/**
 * 服务层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
public interface GoodPriceLogService extends IService<GoodPriceLogEntity> {
    Boolean saveLog(GoodPriceLogDto goodPriceLogDto);

    List<GoodPriceLogVo> getGoodPriceLogVoList(String good_id);


}