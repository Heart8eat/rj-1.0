package com.rj.backendjixian.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rj.backendjixian.mapper.GoodImageMapper;
import com.rj.backendjixian.mapper.GoodMapper;
import com.rj.backendjixian.model.entity.GoodEntity;
import com.rj.backendjixian.model.entity.GoodImageEntity;
import com.rj.backendjixian.model.vo.ImageVo;
import com.rj.backendjixian.service.IImageService;
import com.rj.backendjixian.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ImageServiceImpl extends ServiceImpl<GoodImageMapper, GoodImageEntity> implements IImageService {

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

        //File对象封装文件路径
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().
                getParentFile().getAbsolutePath() + "/upload";
//        System.out.println(pre);

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
            String url = "/upload" + "/" + id + "/" + fileName;
            GoodImageEntity goodImage = GoodImageEntity
                    .builder()
                    .url(url)
                    .width(imageWidth)
                    .height(imageHeight)
                    .main(main)
                    .goodId(id)
                    .build();
            mapper.insert(goodImage);
            root.add(ImageVo.success(url, imageWidth, imageHeight));
        }


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

}
