package com.rj.backendjixian.service.impl;


import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.GoodImageMapper;
import com.rj.backendjixian.mapper.GoodMapper;
import com.rj.backendjixian.mapper.GoodTypeMapper;
import com.rj.backendjixian.model.dto.GoodCreateDto;
import com.rj.backendjixian.model.dto.PublishGoodDto;
import com.rj.backendjixian.model.entity.GoodEntity;
import com.rj.backendjixian.model.entity.GoodImageEntity;
import com.rj.backendjixian.model.vo.GoodBriefVo;
import com.rj.backendjixian.model.vo.GoodDetailsVo;
import com.rj.backendjixian.model.vo.HistoryGoodVo;
import com.rj.backendjixian.model.vo.ImageVo;
import com.rj.backendjixian.service.IGoodService;
import com.rj.backendjixian.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.rj.backendjixian.model.entity.table.GoodEntityTableDef.GOOD_ENTITY;
import static com.rj.backendjixian.model.entity.table.GoodImageEntityTableDef.GOOD_IMAGE_ENTITY;
import static com.rj.backendjixian.model.entity.table.GoodTypeEntityTableDef.GOOD_TYPE_ENTITY;


@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, GoodEntity> implements IGoodService {
    @Autowired
    GoodImageMapper goodImageMapper;
    @Autowired
    GoodTypeMapper goodTypeMapper;

    @Override
    public List<HistoryGoodVo> getHistoryGoodList(String shop_id, String type, String name) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(GOOD_ENTITY.NAME, GOOD_ENTITY.WEIGHT, GOOD_ENTITY.VARIETY, GOOD_ENTITY.STORE,
                        GOOD_ENTITY.SHELF_DATE, GOOD_ENTITY.ID, GOOD_ENTITY.STATUS,
                        GOOD_TYPE_ENTITY.TYPE_NAME.as("type"))
                .from(GOOD_ENTITY)
                .join(GOOD_TYPE_ENTITY).on(GOOD_TYPE_ENTITY.ID.eq(GOOD_ENTITY.TYPE_ID))
                .where(GOOD_ENTITY.SHOP_ID.eq(shop_id))
                .and(GOOD_ENTITY.NAME.like(name).when(name != null))
                .and(GOOD_TYPE_ENTITY.TYPE_NAME.eq(type).when(type != null));
        ;
        return mapper.selectListByQueryAs(queryWrapper, HistoryGoodVo.class,
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(HistoryGoodVo::getImage)
                        .queryWrapper(historyGoodVo ->
                                QueryWrapper.create()
                                        .from(GOOD_IMAGE_ENTITY)
                                        .select(GOOD_IMAGE_ENTITY.URL, GOOD_IMAGE_ENTITY.WIDTH, GOOD_IMAGE_ENTITY.HEIGHT)
                                        .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(historyGoodVo.getId()))
                                        .and(GOOD_IMAGE_ENTITY.MAIN.eq(1))
                        )
        );
    }

    @Override
    public List<GoodBriefVo> getGoodBriefList(String type, String name) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(GOOD_ENTITY.ID, GOOD_ENTITY.NAME, GOOD_ENTITY.PRICE)
                .from(GOOD_ENTITY)
                .join(GOOD_TYPE_ENTITY).on(GOOD_TYPE_ENTITY.ID.eq(GOOD_ENTITY.TYPE_ID))
                .where(GOOD_ENTITY.STATUS.eq(1))
                .and(GOOD_ENTITY.NAME.like(name).when(name != null))
                .and(GOOD_TYPE_ENTITY.TYPE_NAME.eq(type).when(type != null));
        return mapper.selectListByQueryAs(queryWrapper, GoodBriefVo.class,
                fieldQueryBuilder -> fieldQueryBuilder
                        .field(GoodBriefVo::getImage)
                        .queryWrapper(goodBriefVo ->
                                QueryWrapper.create()
                                        .from(GOOD_IMAGE_ENTITY)
                                        .select(GOOD_IMAGE_ENTITY.URL, GOOD_IMAGE_ENTITY.WIDTH, GOOD_IMAGE_ENTITY.HEIGHT)
                                        .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(goodBriefVo.getId()))
                                        .and(GOOD_IMAGE_ENTITY.MAIN.eq(1))
                        )
        );
    }


    @Override
    public Boolean changeGoodStatue(String id, int statue) {
        return this.updateChain()
                .set(GOOD_ENTITY.STATUS, statue)
                .where(GOOD_ENTITY.ID.eq(id))
                .update();
    }

    @Override
    public GoodDetailsVo getGoodDetails(String id) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(GOOD_ENTITY.NAME, GOOD_ENTITY.WEIGHT, GOOD_ENTITY.VARIETY, GOOD_ENTITY.STORE,
                        GOOD_ENTITY.SHELF_DATE, GOOD_ENTITY.ID,GOOD_TYPE_ENTITY.TYPE_NAME.as("type"))
                .join(GOOD_TYPE_ENTITY).on(GOOD_TYPE_ENTITY.ID.eq(GOOD_ENTITY.TYPE_ID))
                .from(GOOD_ENTITY)
                .where(GOOD_ENTITY.ID.eq(id));
        return mapper.selectListByQueryAs(queryWrapper,GoodDetailsVo.class,
                fieldQueryBuilder ->fieldQueryBuilder
                        .field(GoodDetailsVo::getMainImage)
                        .queryWrapper(goodDetailsVo -> QueryWrapper.create()
                                .select(GOOD_IMAGE_ENTITY.URL, GOOD_IMAGE_ENTITY.WIDTH, GOOD_IMAGE_ENTITY.HEIGHT)
                                .from(GOOD_IMAGE_ENTITY)
                                .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(goodDetailsVo.getId()))
                                .and(GOOD_IMAGE_ENTITY.MAIN.eq(1))
                        ),
                fieldQueryBuilder ->fieldQueryBuilder
                        .field(GoodDetailsVo::getImage)
                        .queryWrapper(goodDetailsVo -> QueryWrapper.create()
                                .select(GOOD_IMAGE_ENTITY.URL, GOOD_IMAGE_ENTITY.WIDTH, GOOD_IMAGE_ENTITY.HEIGHT)
                                .from(GOOD_IMAGE_ENTITY)
                                .where(GOOD_IMAGE_ENTITY.GOOD_ID.eq(goodDetailsVo.getId()))
                                .and(GOOD_IMAGE_ENTITY.MAIN.eq(0))
                        )
        ).get(0);
    }

    @Override
    public boolean publish(PublishGoodDto publishGoodDto) {
        int[] row=Db.executeBatch(publishGoodDto.getIds(),GoodMapper.class,
                (mapper,id)-> UpdateChain.of(mapper)
                            .from(GOOD_ENTITY) //使用 mapper 参数，才能起到批量执行的效果
                            .set(GOOD_ENTITY.STATUS, 1)
                            .where(GOOD_ENTITY.ID.eq(id))
                            .and(GOOD_ENTITY.STATUS.eq(0))
                            .update()
                );
        return Arrays.stream(row).anyMatch(r->r>0);
    }

    /**
     * 上传图片组
     *
     * @param multipartFiles
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public List<ImageVo> uploadImgs(MultipartFile[] multipartFiles, String id, Integer main) throws IOException {
        //定义图片后缀格式
        String suffix = ".jpg,.png,.jpeg,.gif";

        //File对象封装文件路径
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().
                getParentFile().getAbsolutePath() + "/upload";
//        System.out.println(pre);

        //用于返回的list<ImageVo>
        List<ImageVo> root = new ArrayList<ImageVo>();

        for (MultipartFile multipartFile : multipartFiles) {

            //获取原始图片名称
            String originalFilename = multipartFile.getOriginalFilename();
            originalFilename = Objects.requireNonNull(originalFilename).toLowerCase();

            //获取图片类型
            String suffixName = FileUtil.getSuffix(originalFilename);
            //验证图片后缀是否满足suffix内定义的后缀
            List<String> strings = Arrays.asList(suffix.split(","));
            //不包含规定的后缀名则直接返回失败
            if (!strings.contains(suffixName)) {
                root.add(ImageVo.fail());
            }

            File file = new File(pre + "/" + id);
            //如果目录不存在创建多级目录
            if (!file.exists()) {
                boolean mkdirs = file.mkdirs();
                System.out.println("日期文件夹" + (mkdirs ? "创建成功" : "创建失败"));
            }
            //判断目录下是否有相同的文件

            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (FileUtil.isEqual(multipartFile, f)) {
                        throw new IOException("相同的文件");
                    }
                }
            }

            //根据是否存在宽高判断是否为图片而不是恶意文件  目前未用
            Integer imageWidth = FileUtil.getImageWidth(multipartFile);
            Integer imageHeight = FileUtil.getImageHeight(multipartFile);
            //文件名 UUID,目前图片名字由前端定义，后端直接采用originalName
            String fileName = FileUtil.getUUIDName(originalFilename);

            //文件上传
            File newFile = new File(file.getAbsolutePath() + "/" + fileName);
            multipartFile.transferTo(newFile);
            //返回相对地址
            String url ="/upload" + "/" + id + "/" + fileName;
            GoodImageEntity goodImage = GoodImageEntity
                    .builder()
                    .url(url)
                    .width(imageWidth)
                    .height(imageHeight)
                    .main(main)
                    .goodId(id)
                    .build();
            goodImageMapper.insert(goodImage);
            root.add(ImageVo.success(url, imageWidth, imageHeight));
        }


        return root;
    }

    @Override
    public boolean deleteImgs(String id) {

        //File对象封装文件路径
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().
                getParentFile().getAbsolutePath() + "/upload";
        System.out.println(pre);

        File file = new File(pre + "/" + id);   //目标文件或文件夹

        boolean flag = FileUtil.deleteFile(file);    //工具类实现删除

        return flag;
    }

    /**
     * 创建新Good
     *
     * @param good
     * @return
     */
    @Override
    public Map<String, String> createGood(GoodCreateDto good) {
        GoodEntity goodEntity = good.dto2Entity();
        QueryWrapper queryWrapper=QueryWrapper.create()
                .from(GOOD_TYPE_ENTITY)
                .select(GOOD_TYPE_ENTITY.ID)
                .where(GOOD_TYPE_ENTITY.TYPE_NAME.eq(good.getType()));
        String typeId=goodTypeMapper.selectObjectByQueryAs(queryWrapper,String.class);
        goodEntity.setTypeId(typeId);
        if (mapper.insert(goodEntity) > 0) {
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
        return null;
    }


}