package com.rj.backendjixian.service.impl;


import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.GoodMapper;
import com.rj.backendjixian.model.entity.GoodEntity;
import com.rj.backendjixian.service.IGoodService;
import org.springframework.stereotype.Service;


@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, GoodEntity> implements IGoodService {

}