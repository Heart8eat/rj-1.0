package com.rj.backendjixian.service.impl;


import org.springframework.stereotype.Service;
import com.rj.backendjixian.service.IGoodService;
import com.rj.backendjixian.entity.GoodEntity;
import com.rj.backendjixian.mapper.GoodMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;


@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, GoodEntity> implements IGoodService {

}