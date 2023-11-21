package com.rj.backendjixian.service.impl;


import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.GoodMapper;
import com.rj.backendjixian.model.dto.GoodCreateDto;
import com.rj.backendjixian.model.dto.GoodUpdateDto;
import com.rj.backendjixian.model.dto.PublishGoodDto;
import com.rj.backendjixian.model.entity.GoodEntity;
import com.rj.backendjixian.model.vo.GoodBriefVo;
import com.rj.backendjixian.model.vo.GoodDetailsVo;
import com.rj.backendjixian.model.vo.HistoryGoodVo;
import com.rj.backendjixian.model.vo.PublishGoodVo;
import com.rj.backendjixian.service.IGoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rj.backendjixian.model.entity.table.GoodEntityTableDef.GOOD_ENTITY;
import static com.rj.backendjixian.model.entity.table.GoodImageEntityTableDef.GOOD_IMAGE_ENTITY;
import static com.rj.backendjixian.model.entity.table.GoodPriceLogEntityTableDef.GOOD_PRICE_LOG_ENTITY;
import static com.rj.backendjixian.model.entity.table.GoodTypeEntityTableDef.GOOD_TYPE_ENTITY;


@Service
@Slf4j
public class GoodServiceImpl extends ServiceImpl<GoodMapper, GoodEntity> implements IGoodService {
    @Autowired
    private GoodImageServiceImpl goodImageService;
    @Autowired
    private GoodTypeServiceImpl goodTypeService;
    @Autowired
    private GoodPriceLogServiceImpl goodPriceLogService;
    public QueryWrapper createGoodQueryWrapper(String goodId, String shopId, String type, String name, QueryCondition ...other) {
        QueryWrapper queryWrapper= QueryWrapper.create()
                .select(GOOD_ENTITY.DEFAULT_COLUMNS, GOOD_TYPE_ENTITY.TYPE_NAME.as("type"))
                .from(GOOD_ENTITY)
                .join(GOOD_TYPE_ENTITY).on(GOOD_TYPE_ENTITY.ID.eq(GOOD_ENTITY.TYPE_ID))
                .where(GOOD_ENTITY.SHOP_ID.eq(shopId).when(shopId!=null))
                .and(GOOD_ENTITY.ID.eq(goodId).when(goodId!=null))
                .and(GOOD_ENTITY.NAME.like(name).when(name != null))
                .and(GOOD_TYPE_ENTITY.TYPE_NAME.eq(type).when(type != null));
        for(QueryCondition qc:other){
            queryWrapper=queryWrapper.and(qc);
        }
        return queryWrapper;
    }
    private QueryWrapper createPriceQueryWrapper(String goodId){
        return  QueryWrapper.create()
                .from(GOOD_PRICE_LOG_ENTITY)
                .select(GOOD_PRICE_LOG_ENTITY.PRICE)
                .where(GOOD_PRICE_LOG_ENTITY.GOOD_ID.eq(goodId))
                .orderBy(GOOD_PRICE_LOG_ENTITY.CREATE_TIME, false)
                .limit(1);
    }
    private QueryWrapper createImageQueryWrapper(String goodId,int main){
        return QueryWrapper.create()
                .select(GOOD_IMAGE_ENTITY.URL, GOOD_IMAGE_ENTITY.WIDTH, GOOD_IMAGE_ENTITY.HEIGHT)
                .from(GOOD_IMAGE_ENTITY)
                .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(goodId))
                .and(GOOD_IMAGE_ENTITY.MAIN.eq(main));
    }
    private QueryWrapper historyGoodQueryWrapper(String shop_id, String type, String name) {
        return createGoodQueryWrapper(null,shop_id,type,name);
    }

    @Override
    @Transactional
    public List<HistoryGoodVo> getHistoryGoodList(String shop_id, String type, String name) {
        QueryWrapper queryWrapper = historyGoodQueryWrapper(shop_id, type, name);
        return mapper.selectListByQueryAs(queryWrapper, HistoryGoodVo.class,
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(HistoryGoodVo::getMainImage)
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
                        .field(HistoryGoodVo::getMainImage)
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
        return  createGoodQueryWrapper(null,shop_id,type,name,
                // 只要待发布商品状态码为0
                GOOD_ENTITY.STATUS.eq(0)
        );



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
        return createGoodQueryWrapper(null,null,type,name,
                // 买家浏览上架的商品
                GOOD_ENTITY.STATUS.eq(1)
        );
    }

    @Override
    @Transactional
    public List<GoodBriefVo> getGoodBriefList(String type, String name) {
        QueryWrapper queryWrapper = goodBriefQueryWrapper(type, name);
        return mapper.selectListByQueryAs(queryWrapper, GoodBriefVo.class,
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(GoodBriefVo::getImage)
                        .queryWrapper(goodBriefVo ->
                                createImageQueryWrapper(goodBriefVo.getId(),1)
                        ),
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(GoodBriefVo::getPrice)
                        .queryWrapper(goodBriefVo ->
                                createPriceQueryWrapper(goodBriefVo.getId())
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
                                createImageQueryWrapper(goodBriefVo.getId(),1)
                        ),
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(GoodBriefVo::getPrice)
                        .queryWrapper(goodBriefVo ->
                                createPriceQueryWrapper(goodBriefVo.getId())
                        )
        );
    }

    /**
     * 更改商品状态
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
        QueryWrapper queryWrapper = createGoodQueryWrapper(id,null,null,null);
        return mapper.selectListByQueryAs(queryWrapper, GoodDetailsVo.class,
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(GoodDetailsVo::getMainImage)
                        .queryWrapper(goodDetailsVo ->
                                createImageQueryWrapper(goodDetailsVo.getId(),1)
                        ),
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(GoodDetailsVo::getImages)
                        .queryWrapper(goodDetailsVo ->
                                createImageQueryWrapper(goodDetailsVo.getId(),0)
                        ),
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(GoodDetailsVo::getPrice)
                        .queryWrapper(goodDetailsVo ->
                                createPriceQueryWrapper(goodDetailsVo.getId())
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateGood(GoodUpdateDto goodUpdateDto) {
        GoodEntity goodEntity=goodUpdateDto.dto2Entity();
        if(goodUpdateDto.getType()!=null){
            // 要更新类别
            String typeId= goodTypeService.getTypeIdOrCreate(goodUpdateDto.getType());
            goodEntity.setTypeId(typeId);
        }
        // TODO 要更新品种
        try{
            int row=mapper.update(goodEntity);
            if(row<1){
                throw new RuntimeException("商品基本信息更新失败，数据库回滚");
            }
        }catch(UncategorizedSQLException ignored){
            log.info("商品基本信息无需更新");
        }

        if(goodUpdateDto.getFakeId()!=null){
            //需要更改图片信息
            //删除之前商品所有的图片记录
            if(!goodImageService.deleteAllGoodImageByGoodId(goodEntity.getId())){
                throw new RuntimeException("删除旧商品图片失败，开始回滚");
            }
            // 把数据库中id为假id的更新为现在商品生成的id
            if(!goodImageService.bindGoodId(goodUpdateDto.getFakeId(),goodEntity.getId())){
                throw new RuntimeException("商品图片绑定失败，开始回滚");
            }
        }
        if(goodUpdateDto.getPrice()!=null){
            // 插入新价格记录
            boolean b=goodPriceLogService.save(goodUpdateDto.getId(),goodUpdateDto.getPrice());
            if(!b){
                throw new RuntimeException("商品价格信息更新失败，数据库回滚");
            }
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

        // 类别
        String typeId = goodTypeService.getTypeIdOrCreate(good.getType());
        // TODO 品种

        GoodEntity goodEntity = good.dto2Entity();
        goodEntity.setTypeId(typeId);
        if (mapper.insert(goodEntity) > 0) {

            // 价格表中插入价格

            if (!goodPriceLogService.save(goodEntity.getId(),good.getPrice())) {
                throw new RuntimeException("价格插入失败，商品创建失败，开始回滚");
            }

            // 把数据库中id为假id的更新为现在商品生成的id
            if(!goodImageService.bindGoodId(good.getFakeId(),goodEntity.getId())){
                throw new RuntimeException("商品图片绑定失败，开始回滚");
            }

            Map<String, String> map = new HashMap<>();
            map.put("goodId", goodEntity.getId());
            return map;
        }
        throw new RuntimeException("商品插入失败，开始回滚");
    }


}