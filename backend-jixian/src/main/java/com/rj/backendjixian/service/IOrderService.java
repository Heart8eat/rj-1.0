package com.rj.backendjixian.service;


import com.mybatisflex.core.service.IService;
import com.rj.backendjixian.model.entity.OrderEntity;
import com.rj.backendjixian.model.vo.HistoryOrderVo;
import com.rj.backendjixian.model.vo.OrderVo;

import java.util.List;

/**
 * 服务层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
public interface IOrderService extends IService<OrderEntity> {

    //传入一个shops的id，从goods、          buyers、         orders表中
    //  分别取出      name、image;   name、address、phone；   pay；
    List<HistoryOrderVo> getHistoryOrders(String id);
}