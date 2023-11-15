package com.rj.backendjixian.service.impl;


import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.GoodImageMapper;
import com.rj.backendjixian.mapper.GoodMapper;
import com.rj.backendjixian.mapper.GoodPriceLogMapper;
import com.rj.backendjixian.mapper.GoodTypeMapper;
import com.rj.backendjixian.model.dto.GoodCreateDto;
import com.rj.backendjixian.model.dto.PublishGoodDto;
import com.rj.backendjixian.model.entity.GoodEntity;
import com.rj.backendjixian.model.entity.GoodImageEntity;
import com.rj.backendjixian.model.entity.GoodPriceLogEntity;
import com.rj.backendjixian.model.vo.*;
import com.rj.backendjixian.service.IGoodService;
import com.rj.backendjixian.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.rj.backendjixian.model.entity.table.GoodEntityTableDef.GOOD_ENTITY;
import static com.rj.backendjixian.model.entity.table.GoodImageEntityTableDef.GOOD_IMAGE_ENTITY;
import static com.rj.backendjixian.model.entity.table.GoodPriceLogEntityTableDef.GOOD_PRICE_LOG_ENTITY;
import static com.rj.backendjixian.model.entity.table.GoodTypeEntityTableDef.GOOD_TYPE_ENTITY;


@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, GoodEntity> implements IGoodService {
    @Autowired
    GoodImageMapper goodImageMapper;
    @Autowired
    GoodTypeMapper goodTypeMapper;
    @Autowired
    GoodPriceLogMapper goodPriceLogMapper;

    private QueryWrapper historyGoodQueryWrapper(String shop_id, String type, String name) {
        return QueryWrapper.create()
                .select(GOOD_ENTITY.NAME, GOOD_ENTITY.WEIGHT, GOOD_ENTITY.VARIETY, GOOD_ENTITY.STORE,
                        GOOD_ENTITY.SHELF_DATE, GOOD_ENTITY.ID, GOOD_ENTITY.STATUS,
                        GOOD_TYPE_ENTITY.TYPE_NAME.as("type"))
                .from(GOOD_ENTITY)
                .join(GOOD_TYPE_ENTITY).on(GOOD_TYPE_ENTITY.ID.eq(GOOD_ENTITY.TYPE_ID))
                .where(GOOD_ENTITY.SHOP_ID.eq(shop_id))
                .and(GOOD_ENTITY.NAME.like(name).when(name != null))
                .and(GOOD_TYPE_ENTITY.TYPE_NAME.eq(type).when(type != null));
    }

    @Override
    @Transactional
    public List<HistoryGoodVo> getHistoryGoodList(String shop_id, String type, String name) {
        QueryWrapper queryWrapper = historyGoodQueryWrapper(shop_id, type, name);
        return mapper.selectListByQueryAs(queryWrapper, HistoryGoodVo.class,
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(HistoryGoodVo::getImage)
                        .queryWrapper(historyGoodVo ->
                                QueryWrapper.create()
                                        .from(GOOD_IMAGE_ENTITY)
                                        .select(GOOD_IMAGE_ENTITY.URL, GOOD_IMAGE_ENTITY.WIDTH, GOOD_IMAGE_ENTITY.HEIGHT)
                                        .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(historyGoodVo.getId()))
                                        // 只要主图片
                                        .and(GOOD_IMAGE_ENTITY.MAIN.eq(1))
                        )
        );
    }

    @Override
    @Transactional
    public Page<HistoryGoodVo> getHistoryGoodPage(Page<HistoryGoodVo> page, String shop_id, String type, String name) {
        QueryWrapper queryWrapper = historyGoodQueryWrapper(shop_id, type, name);
        return mapper.paginateAs(page, queryWrapper, HistoryGoodVo.class,
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(HistoryGoodVo::getImage)
                        .queryWrapper(historyGoodVo ->
                                QueryWrapper.create()
                                        .from(GOOD_IMAGE_ENTITY)
                                        .select(GOOD_IMAGE_ENTITY.URL, GOOD_IMAGE_ENTITY.WIDTH, GOOD_IMAGE_ENTITY.HEIGHT)
                                        .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(historyGoodVo.getId()))
                                        // 只要主图片
                                        .and(GOOD_IMAGE_ENTITY.MAIN.eq(1))
                        )
        );
    }

    private QueryWrapper publishGoodQueryWrapper(String shop_id, String type, String name) {
        return QueryWrapper.create()
                .select(GOOD_ENTITY.NAME, GOOD_ENTITY.WEIGHT, GOOD_ENTITY.VARIETY, GOOD_ENTITY.STORE,
                        GOOD_ENTITY.SHELF_DATE, GOOD_ENTITY.ID,
                        GOOD_TYPE_ENTITY.TYPE_NAME.as("type"))
                .from(GOOD_ENTITY)
                .join(GOOD_TYPE_ENTITY).on(GOOD_TYPE_ENTITY.ID.eq(GOOD_ENTITY.TYPE_ID))
                .where(GOOD_ENTITY.SHOP_ID.eq(shop_id))
                // 只要待发布商品状态码为0
                .and(GOOD_ENTITY.STATUS.eq(0))
                .and(GOOD_ENTITY.NAME.like(name).when(name != null))
                .and(GOOD_TYPE_ENTITY.TYPE_NAME.eq(type).when(type != null));
    }

    @Override
    @Transactional
    public List<PublishGoodVo> getPublishGoodList(String shop_id, String type, String name) {
        QueryWrapper queryWrapper = publishGoodQueryWrapper(shop_id, type, name);
        return mapper.selectListByQueryAs(queryWrapper, PublishGoodVo.class,
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(PublishGoodVo::getImage)
                        .queryWrapper(publishGoodVo ->
                                QueryWrapper.create()
                                        .from(GOOD_IMAGE_ENTITY)
                                        .select(GOOD_IMAGE_ENTITY.URL, GOOD_IMAGE_ENTITY.WIDTH, GOOD_IMAGE_ENTITY.HEIGHT)
                                        .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(publishGoodVo.getId()))
                                        // 只要主图片
                                        .and(GOOD_IMAGE_ENTITY.MAIN.eq(1))
                        )
        );
    }

    @Override
    @Transactional
    public Page<PublishGoodVo> getPublishGoodPage(Page<PublishGoodVo> page, String shop_id, String type, String name) {
        QueryWrapper queryWrapper = publishGoodQueryWrapper(shop_id, type, name);
        return mapper.paginateAs(page, queryWrapper, PublishGoodVo.class,
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(PublishGoodVo::getImage)
                        .queryWrapper(publishGoodVo ->
                                QueryWrapper.create()
                                        .from(GOOD_IMAGE_ENTITY)
                                        .select(GOOD_IMAGE_ENTITY.URL, GOOD_IMAGE_ENTITY.WIDTH, GOOD_IMAGE_ENTITY.HEIGHT)
                                        .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(publishGoodVo.getId()))
                                        // 只要主图片
                                        .and(GOOD_IMAGE_ENTITY.MAIN.eq(1))
                        )
        );
    }

    private QueryWrapper goodBriefQueryWrapper(String type, String name) {
        return QueryWrapper.create()
                .select(GOOD_ENTITY.ID, GOOD_ENTITY.NAME)
                .from(GOOD_ENTITY)
                .join(GOOD_TYPE_ENTITY).on(GOOD_TYPE_ENTITY.ID.eq(GOOD_ENTITY.TYPE_ID))
                // 这里查询的是买家浏览的商品，所以是上架的商品
                .where(GOOD_ENTITY.STATUS.eq(1))
                .and(GOOD_ENTITY.NAME.like(name).when(name != null))
                .and(GOOD_TYPE_ENTITY.TYPE_NAME.eq(type).when(type != null));
    }

    @Override
    @Transactional
    public List<GoodBriefVo> getGoodBriefList(String type, String name) {
        QueryWrapper queryWrapper = goodBriefQueryWrapper(type, name);
        return mapper.selectListByQueryAs(queryWrapper, GoodBriefVo.class,
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(GoodBriefVo::getImage)
                        .queryWrapper(goodBriefVo ->
                                QueryWrapper.create()
                                        .from(GOOD_IMAGE_ENTITY)
                                        .select(GOOD_IMAGE_ENTITY.URL, GOOD_IMAGE_ENTITY.WIDTH, GOOD_IMAGE_ENTITY.HEIGHT)
                                        .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(goodBriefVo.getId()))
                                        .and(GOOD_IMAGE_ENTITY.MAIN.eq(1))
                        ),
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(GoodBriefVo::getPrice)
                        .queryWrapper(goodBriefVo ->
                                QueryWrapper.create()
                                        .from(GOOD_PRICE_LOG_ENTITY)
                                        .select(GOOD_PRICE_LOG_ENTITY.PRICE)
                                        .where(GOOD_PRICE_LOG_ENTITY.GOOD_ID.eq(goodBriefVo.getId()))
                                        .orderBy(GOOD_PRICE_LOG_ENTITY.CREATE_TIME, false)
                                        .limit(1)
                        )
        );
    }

    /**
     * 分页查询商品的简略信息
     *
     * @param page 分页对象
     * @param type 类别
     * @param name 商品名
     * @return page 分页对象
     */
    @Override
    @Transactional
    public Page<GoodBriefVo> getGoodBriefPage(Page<GoodBriefVo> page, String type, String name) {
        QueryWrapper queryWrapper = goodBriefQueryWrapper(type, name);
        return mapper.paginateAs(page, queryWrapper, GoodBriefVo.class,
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(GoodBriefVo::getImage)
                        .queryWrapper(goodBriefVo ->
                                QueryWrapper.create()
                                        .from(GOOD_IMAGE_ENTITY)
                                        .select(GOOD_IMAGE_ENTITY.URL, GOOD_IMAGE_ENTITY.WIDTH, GOOD_IMAGE_ENTITY.HEIGHT)
                                        .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(goodBriefVo.getId()))
                                        .and(GOOD_IMAGE_ENTITY.MAIN.eq(1))
                        ),
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(GoodBriefVo::getPrice)
                        .queryWrapper(goodBriefVo ->
                                QueryWrapper.create()
                                        .from(GOOD_PRICE_LOG_ENTITY)
                                        .select(GOOD_PRICE_LOG_ENTITY.PRICE)
                                        .where(GOOD_PRICE_LOG_ENTITY.GOOD_ID.eq(goodBriefVo.getId()))
                                        .orderBy(GOOD_PRICE_LOG_ENTITY.CREATE_TIME, false)
                                        .limit(1)
                        )
        );
    }

    /**
     * 根据主键查询商品的详细信息
     *
     * @param id
     * @param statue 要更改的状态
     * @return true 更改成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean changeGoodStatue(String id, int statue) {
        return this.updateChain()
                .set(GOOD_ENTITY.STATUS, statue)
                .where(GOOD_ENTITY.ID.eq(id))
                .update();
    }

    /**
     * 根据主键查询商品的详细信息
     *
     * @param id
     * @return 商品的详细信息
     */
    @Override
    @Transactional
    public GoodDetailsVo getGoodDetails(String id) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(GOOD_ENTITY.NAME, GOOD_ENTITY.WEIGHT, GOOD_ENTITY.VARIETY, GOOD_ENTITY.STORE,
                        GOOD_ENTITY.SHELF_DATE, GOOD_ENTITY.ID, GOOD_TYPE_ENTITY.TYPE_NAME.as("type"))
                .join(GOOD_TYPE_ENTITY).on(GOOD_TYPE_ENTITY.ID.eq(GOOD_ENTITY.TYPE_ID))
                .from(GOOD_ENTITY)
                .where(GOOD_ENTITY.ID.eq(id));
        return mapper.selectListByQueryAs(queryWrapper, GoodDetailsVo.class,
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(GoodDetailsVo::getMainImage)
                        .queryWrapper(goodDetailsVo -> QueryWrapper.create()
                                .select(GOOD_IMAGE_ENTITY.URL, GOOD_IMAGE_ENTITY.WIDTH, GOOD_IMAGE_ENTITY.HEIGHT)
                                .from(GOOD_IMAGE_ENTITY)
                                .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(goodDetailsVo.getId()))
                                .and(GOOD_IMAGE_ENTITY.MAIN.eq(1))
                        ),
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(GoodDetailsVo::getImage)
                        .queryWrapper(goodDetailsVo -> QueryWrapper.create()
                                .select(GOOD_IMAGE_ENTITY.URL, GOOD_IMAGE_ENTITY.WIDTH, GOOD_IMAGE_ENTITY.HEIGHT)
                                .from(GOOD_IMAGE_ENTITY)
                                .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(goodDetailsVo.getId()))
                                .and(GOOD_IMAGE_ENTITY.MAIN.eq(0))
                        ),
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(GoodDetailsVo::getPrice)
                        .queryWrapper(goodBriefVo ->
                                QueryWrapper.create()
                                        .from(GOOD_PRICE_LOG_ENTITY)
                                        .select(GOOD_PRICE_LOG_ENTITY.PRICE)
                                        .where(GOOD_PRICE_LOG_ENTITY.GOOD_ID.eq(goodBriefVo.getId()))
                                        .orderBy(GOOD_PRICE_LOG_ENTITY.CREATE_TIME, false)
                                        .limit(1)
                        )
        ).get(0);
    }

    /**
     * 批量发布商品
     *
     * @param publishGoodDto
     * @return true 存在发布成功的商品
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean publish(PublishGoodDto publishGoodDto) {
        int[] row = Db.executeBatch(publishGoodDto.getIds(), GoodMapper.class,
                (mapper, id) -> UpdateChain.of(mapper)
                        .from(GOOD_ENTITY) //使用 mapper 参数，才能起到批量执行的效果
                        .set(GOOD_ENTITY.STATUS, 1)
                        .where(GOOD_ENTITY.ID.eq(id))
                        .and(GOOD_ENTITY.STATUS.eq(0))
                        .update()
        );
        if (Arrays.stream(row).anyMatch(r -> r == 0)) {
            throw new RuntimeException("存在未发布成功商品，数据库回滚");
        }
        return true;
    }

    /**
     * 创建新Good
     *
     * @param good
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> createGood(GoodCreateDto good) {
        GoodEntity goodEntity = good.dto2Entity();
        QueryWrapper queryWrapper = QueryWrapper.create()
                .from(GOOD_TYPE_ENTITY)
                .select(GOOD_TYPE_ENTITY.ID)
                .where(GOOD_TYPE_ENTITY.TYPE_NAME.eq(good.getType()));
        String typeId = goodTypeMapper.selectObjectByQueryAs(queryWrapper, String.class);
        goodEntity.setTypeId(typeId);
        if (mapper.insert(goodEntity) > 0) {

            // 价格表中插入价格
            GoodPriceLogEntity goodPriceLog = GoodPriceLogEntity
                    .builder()
                    .price(good.getPrice())
                    .goodId(goodEntity.getId())
                    .build();
            if (goodPriceLogMapper.insert(goodPriceLog) == 0) {
                throw new RuntimeException("价格插入失败，商品创建失败，开始回滚");
            }

            // 把数据库中id为假id的更新为现在商品生成的id
            UpdateChain.create(goodImageMapper)
                    .from(GOOD_IMAGE_ENTITY)
                    .set(GOOD_IMAGE_ENTITY.GOOD_ID, goodEntity.getId())
                    .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(good.getFakeId()))
                    .update();


            Map<String, String> map = new HashMap<>();
            map.put("goodId", goodEntity.getId());
            return map;
        }
        throw new RuntimeException("商品插入失败，开始回滚");
    }


}