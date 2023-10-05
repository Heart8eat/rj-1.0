package com.rj.backendjixian.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.rj.backendjixian.config.FileUploadConfig;
import com.rj.backendjixian.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileServiceImpl implements IFileService {
    @Autowired
    FileUploadConfig fileUploadConfig;
    @Override
    public String upload(MultipartFile file) throws IOException {
        //获取文件原始名称
        String originalFilename = file.getOriginalFilename();
        //获取文件的类型
        String type = FileTypeUtil.getType(file.getInputStream());
        if(fileUploadConfig.getPermittedFileType().stream().noneMatch(at-> at.equals(type))){
            throw new IOException("文件类型不允许！");
        }


        //判断上传文件目录是否存在
        File uploadParentFile = new File(fileUploadConfig.getFileUploadPath());
        if(!uploadParentFile.exists()) {
            //如果不存在就创建文件夹
            uploadParentFile.mkdirs();
        }
        File[] exitsFiles=uploadParentFile.listFiles();
        if(exitsFiles!=null){
            if(Arrays.stream(exitsFiles).anyMatch(exitsFile->
            {
                try {
                    return SecureUtil.md5(exitsFile).equals(SecureUtil.md5(file.getInputStream()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            })){
                throw new IOException("存在相同的文件!");
            }
        }

        //定义一个文件唯一标识码（UUID）
        String uuid = UUID.randomUUID().toString();

        File uploadFile = new File(uploadParentFile.getCanonicalPath()+ StrUtil.BACKSLASH + uuid + StrUtil.DOT + type);
        //将临时文件转存到指定磁盘位置
        file.transferTo(uploadFile);

        return uuid + StrUtil.DOT + type;
    }

    @Override
    public List<String> batchUpload(MultipartFile[] files) throws IOException {
        List<String> fileNames=new ArrayList<>();
        for(MultipartFile file:files){
            fileNames.add(upload(file));
        }
        return fileNames;
    }
}
