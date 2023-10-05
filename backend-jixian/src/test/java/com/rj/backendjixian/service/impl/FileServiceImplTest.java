package com.rj.backendjixian.service.impl;

import cn.hutool.core.io.FileUtil;
import com.rj.backendjixian.service.IFileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;

@SpringBootTest
class FileServiceImplTest {
    @Autowired
    IFileService fileService;
    @Test
    void upload() throws IOException {
        BufferedInputStream in = FileUtil.getInputStream("C:\\Users\\tiantian\\Pictures\\屏幕截图 2023-03-09 112615.png");
        MultipartFile file=new MockMultipartFile("1",in);
        fileService.upload(file);
    }

    @Test
    void uploadList() throws IOException {
        BufferedInputStream in1 = FileUtil.getInputStream("C:\\Users\\tiantian\\Pictures\\屏幕截图 2023-03-09 114836.png");
        MultipartFile file1=new MockMultipartFile("1",in1);
        BufferedInputStream in2 = FileUtil.getInputStream("C:\\Users\\tiantian\\Pictures\\屏幕截图 2023-03-09 112615.png");
        MultipartFile file2=new MockMultipartFile("1",in2);
        fileService.batchUpload(new MultipartFile[]{file1,file2});
    }
}