package com.rj.backendjixian.service;


import com.mybatisflex.core.service.IService;
import com.rj.backendjixian.model.entity.BuyerEntity;
import com.rj.backendjixian.model.vo.OrderVo;

import java.util.List;


public interface IBuyerService extends IService<BuyerEntity> {
    List<OrderVo> getBuyerOrders(String id);
}