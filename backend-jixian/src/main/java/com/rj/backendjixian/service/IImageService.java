package com.rj.backendjixian.service;

import com.mybatisflex.core.service.IService;
import com.rj.backendjixian.model.entity.GoodImageEntity;
import com.rj.backendjixian.model.vo.ImageVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService  extends IService<GoodImageEntity> {

    boolean deleteImgs(String id);

    List<ImageVo> uploadImg(MultipartFile[] multipartFiles, String id, Integer main, List<ImageVo> root) throws IOException;

    List<ImageVo> uploadImgs(MultipartFile[] multipartFiles, MultipartFile mainFile, String id, List<ImageVo> root) throws IOException;

}
