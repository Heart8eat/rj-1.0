package com.rj.backendjixian.service;

import com.rj.backendjixian.model.vo.ImageVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService  {

    boolean deleteImgs(String id);
    boolean deleteImgByUrl(String url);

    List<ImageVo> uploadImg(MultipartFile[] multipartFiles, String id, Integer main, List<ImageVo> root) throws IOException;

    List<ImageVo> uploadImgs(MultipartFile[] multipartFiles, MultipartFile mainFile, String id, List<ImageVo> root) throws IOException;


}
