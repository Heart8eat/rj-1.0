package com.rj.backendjixian.service.impl;


import org.springframework.stereotype.Service;
import com.rj.backendjixian.service.IBuyerService;
import com.rj.backendjixian.entity.BuyerEntity;
import com.rj.backendjixian.mapper.BuyerMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;


@Service
public class BuyerServiceImpl extends ServiceImpl<BuyerMapper, BuyerEntity> implements IBuyerService {

}