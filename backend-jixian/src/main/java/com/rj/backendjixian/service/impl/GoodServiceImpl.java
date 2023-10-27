package com.rj.backendjixian.service.impl;


import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.GoodMapper;
import com.rj.backendjixian.model.dto.GoodCreateDto;
import com.rj.backendjixian.model.entity.GoodEntity;
import com.rj.backendjixian.model.vo.GoodBriefVo;
import com.rj.backendjixian.model.vo.HistoryGoodVo;
import com.rj.backendjixian.model.vo.ImageVo;
import com.rj.backendjixian.service.IGoodService;
import com.rj.backendjixian.util.FileUtil;
import lombok.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.rj.backendjixian.model.entity.table.GoodEntityTableDef.GOOD_ENTITY;


@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, GoodEntity> implements IGoodService {

    @Override
    public List<HistoryGoodVo> getHistoryGoodList(String shop_id) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(GOOD_ENTITY.DEFAULT_COLUMNS)
                .from(GOOD_ENTITY)
                .where(GOOD_ENTITY.SHOP_ID.eq(shop_id));
        List<GoodEntity> goodEntities = mapper.selectListByQuery(queryWrapper);
        return goodEntities.stream()
                .map(HistoryGoodVo::new).toList();
    }

    @Override
    public List<GoodBriefVo> getGoodBriefList() {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(GOOD_ENTITY.ID, GOOD_ENTITY.NAME, GOOD_ENTITY.PRICE,
                        GOOD_ENTITY.IMAGE)
                .from(GOOD_ENTITY)
                .where(GOOD_ENTITY.STATUS.eq(1));
        List<GoodEntity> goodEntities = mapper.selectListByQuery(queryWrapper);
        return goodEntities.stream()
                .map(GoodBriefVo::new).toList();
    }



    @Override
    public Boolean changeGoodStatue(String id,int statue) {
        return this.updateChain()
                .set(GOOD_ENTITY.STATUS,statue)
                .where(GOOD_ENTITY.ID.eq(id))
                .update();
    }

    /**
     * 上传图片组
     * @param multipartFiles
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public List<ImageVo> uploadImgs(MultipartFile[] multipartFiles, String id) throws IOException {
        //定义图片后缀格式
        String suffix = ".jpg,.png,.jpeg,.gif";

        //File对象封装文件路径
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().
                getParentFile().getAbsolutePath() + "/upload";
        System.out.println(pre);

        //用于返回的list<ImageVo>
        List<ImageVo> root = new ArrayList<ImageVo>();

        for(MultipartFile multipartFile : multipartFiles){

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

            //根据是否存在宽高判断是否为图片而不是恶意文件  目前未用
            Integer imageWidth = FileUtil.getImageWidth(multipartFile);
            Integer imageHeight = FileUtil.getImageHeight(multipartFile);
            //文件名 UUID,目前图片名字由前端定义，后端直接采用originalName
            //String fileName = FileUtil.getUUIDName(originalFilename);

            //文件上传
            File newFile = new File(file.getAbsolutePath()+"/"+originalFilename);
            multipartFile.transferTo(newFile);

            root.add(ImageVo.success(newFile.getAbsolutePath(), imageWidth,imageHeight));
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
     * @param good
     * @return
     */
    @Override
    public Map<String,String> createGood(GoodCreateDto good){
        GoodEntity goodEntity = good.dto2Entity();
        if(mapper.insert(goodEntity) > 0){
            Map<String,String> map = new HashMap<>();
            map.put("goodId", goodEntity.getId());
            return map;
        }
        return null;
    }


}