package com.rj.backendjixian.service;


import com.mybatisflex.core.service.IService;
import com.rj.backendjixian.model.dto.BuyerCreateDto;
import com.rj.backendjixian.model.entity.BuyerEntity;
import com.rj.backendjixian.model.vo.OrderVo;

import java.util.List;
import java.util.Map;


public interface IBuyerService extends IService<BuyerEntity> {
    List<OrderVo> getBuyerOrders(String id);

    Map<String, String> createBuyer(BuyerCreateDto buyer);
}