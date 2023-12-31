package com.rj.backendjixian.service.impl;

import com.rj.backendjixian.mapper.GoodImageMapper;
import com.rj.backendjixian.model.entity.GoodImageEntity;
import com.rj.backendjixian.model.vo.ImageVo;
import com.rj.backendjixian.service.IImageService;
import com.rj.backendjixian.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.rj.backendjixian.model.entity.table.GoodImageEntityTableDef.GOOD_IMAGE_ENTITY;

@Service
@Slf4j
public class ImageServiceImpl implements IImageService {
    @Autowired
    private GoodImageServiceImpl goodImageService;
    //File对象封装文件路径
    private final String pre=new ApplicationHome(this.getClass()).getDir().getAbsolutePath() + "/upload";


    /**
     * 上传图片组
     *
     * @param multipartFiles
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public List<ImageVo> uploadImg(MultipartFile[] multipartFiles, String id, Integer main, List<ImageVo> root) throws IOException {
        //定义图片后缀格式
        String suffix = ".jpg,.png,.jpeg,.gif";



//        System.out.println(pre);
        List<GoodImageEntity> goodImageEntities=new ArrayList<>();
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
               log.info("日期文件夹" + (mkdirs ? "创建成功" : "创建失败"));
            }
            //判断目录下是否有相同的文件, 主图片的文件可以和其他图片相同
            if(main==0){
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        if (FileUtil.isEqual(multipartFile, f)) {
                            throw new IOException("相同的文件");
                        }
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
            String url = "/upload" + "/" + id + "/" + fileName;
            GoodImageEntity goodImage = GoodImageEntity
                    .builder()
                    .url(url)
                    .width(imageWidth)
                    .height(imageHeight)
                    .main(main)
                    .goodId(id)
                    .build();
            goodImageEntities.add(goodImage);
            root.add(ImageVo.success(url, imageWidth, imageHeight));
        }
        goodImageService.saveBatch(goodImageEntities);

        return root;
    }


    /**
     * 上传主图片mainFile和图片组multipartFiles
     * @param multipartFiles
     * @param mainFile
     * @param id
     * @param root
     * @return
     * @throws IOException
     */
    @Override
    public List<ImageVo> uploadImgs(MultipartFile[] multipartFiles, MultipartFile mainFile, String id, List<ImageVo> root) throws IOException {
        root = uploadImg(multipartFiles, id, 0, root);
        MultipartFile[] img = new MultipartFile[]{mainFile};
        root = uploadImg(img, id, 1, root);
        return root;
    }


    /**
     * 删除文件夹及其下的所有文件
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteImgs(String id) {
        // 删除数据库中的记录
        if(!goodImageService.remove(GOOD_IMAGE_ENTITY.URL.likeRaw("/upload/"+id+"/%"))){
            throw new RuntimeException("图片删除失败，数据库回滚");
        }
        File file = new File(pre + "/" + id);   //目标文件或文件夹

        return FileUtil.deleteFile(file);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteImgByUrl(String url) {
        if(!goodImageService.remove(GOOD_IMAGE_ENTITY.URL.eq(url))){
            throw new RuntimeException("图片删除失败，数据库回滚");
        }
        File f= cn.hutool.core.io.FileUtil.file(pre+url);
        return cn.hutool.core.io.FileUtil.del(f);
    }

}
