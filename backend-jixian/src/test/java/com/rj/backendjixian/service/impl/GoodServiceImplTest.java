package com.rj.backendjixian.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.rj.backendjixian.model.entity.GoodEntity;
import com.rj.backendjixian.model.vo.GoodBriefVo;
import com.rj.backendjixian.model.vo.HistoryGoodVo;
import com.rj.backendjixian.service.IGoodService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.rj.backendjixian.model.entity.table.GoodEntityTableDef.GOOD_ENTITY;

@Slf4j
@SpringBootTest
@Transactional
class GoodServiceImplTest {
    @Autowired
    private IGoodService service;
    private String id="630e807bdf604941b89192af2eb7396e";
    private final GoodEntity goodEntity=GoodEntity.builder()
            .name("凤梨")
            .image("1.png,2.png,3.png,4.png")
            .description("这是凤梨")
            .status(1)
            .price(100f)
            .quantity(0)
            .store("室温")
            .shopId("630e807bdf604941b89192af2eb7396e")
            .variety("菠萝")
            .weight("1kg")
            .type("类别")
            .build();
    @BeforeEach
    void before(){
        service.save(goodEntity);
    }
    @Test
    void getHistoryGoodList() {
        List<HistoryGoodVo> historygoodList=service.getHistoryGoodList(id);
        Assertions.assertEquals(5,historygoodList.size());
        Assertions.assertTrue(historygoodList.stream().anyMatch((hg)-> hg.getId().equals(goodEntity.getId())));
    }

    @Test
    void getGoodBriefList() {
        List<GoodBriefVo> goodBriefVoList=service.getGoodBriefList();
        Assertions.assertEquals(2,goodBriefVoList.size());
        Assertions.assertTrue(goodBriefVoList.stream().anyMatch((gb)-> gb.getId().equals(goodEntity.getId())));
    }

    @Test
    void changeGoodStatue() {
        int change=2;
        Boolean res=service.changeGoodStatue(goodEntity.getId(),change);
        Assertions.assertEquals(true,res);
        int statues=service.getObjAs(QueryWrapper.create()
                .from(GOOD_ENTITY)
                .select(GOOD_ENTITY.STATUS)
                .where(GOOD_ENTITY.ID.eq(goodEntity.getId())),Integer.class);
        Assertions.assertEquals(change,statues);
    }
}