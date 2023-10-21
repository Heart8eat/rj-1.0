package com.rj.backendjixian.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.GoodOrdersMapper;
import com.rj.backendjixian.model.entity.GoodOrderEntity;
import com.rj.backendjixian.service.IGoodOrderService;
import org.springframework.stereotype.Service;

@Service
public class GoodOrderServiceImpl extends ServiceImpl<GoodOrdersMapper, GoodOrderEntity> implements IGoodOrderService {

}
