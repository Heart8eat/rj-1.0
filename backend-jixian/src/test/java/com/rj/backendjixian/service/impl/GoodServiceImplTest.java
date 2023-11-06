package com.rj.backendjixian.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
class GoodServiceImplTest {
//    private final GoodEntity goodEntity = GoodEntity.builder()
//            .name("凤梨")
//            .description("这是凤梨")
//            .status(1)
//
//            .quantity(0)
//            .store("室温")
//            .shopId("630e807bdf604941b89192af2eb7396e")
//            .variety("菠萝")
//            .weight("1kg")
//            .build();
//    @Autowired
//    private IGoodService service;
//    private String id = "630e807bdf604941b89192af2eb7396e";
//
//    @BeforeEach
//    void before() {
//        service.save(goodEntity);
//    }
//
//    @Test
//    void getHistoryGoodList() {
//        List<HistoryGoodVo> historygoodList = service.getHistoryGoodList(id, null, null);
//        Assertions.assertEquals(5, historygoodList.size());
//        Assertions.assertTrue(historygoodList.stream().anyMatch((hg) -> hg.getId().equals(goodEntity.getId())));
//    }
//
//    @Test
//    void getGoodBriefList() {
//        List<GoodBriefVo> goodBriefVoList = service.getGoodBriefList(null, null);
//        Assertions.assertEquals(2, goodBriefVoList.size());
//        Assertions.assertTrue(goodBriefVoList.stream().anyMatch((gb) -> gb.getId().equals(goodEntity.getId())));
//    }
//
//    @Test
//    void changeGoodStatue() {
//        int change = 2;
//        Boolean res = service.changeGoodStatue(goodEntity.getId(), change);
//        Assertions.assertEquals(true, res);
//        int statues = service.getObjAs(QueryWrapper.create()
//                .from(GOOD_ENTITY)
//                .select(GOOD_ENTITY.STATUS)
//                .where(GOOD_ENTITY.ID.eq(goodEntity.getId())), Integer.class);
//        Assertions.assertEquals(change, statues);
//    }
}