package com.rj.backendjixian.service.impl;


import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.entity.BuyerEntity;
import com.rj.backendjixian.mapper.BuyerMapper;
import com.rj.backendjixian.service.IBuyerService;
import org.springframework.stereotype.Service;


@Service
public class BuyerServiceImpl extends ServiceImpl<BuyerMapper, BuyerEntity> implements IBuyerService {

}