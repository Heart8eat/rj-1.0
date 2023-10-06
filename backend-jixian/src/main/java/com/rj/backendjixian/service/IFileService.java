package com.rj.backendjixian.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileService {
    String upload(MultipartFile file) throws IOException;
    List<String> batchUpload(MultipartFile[] files) throws IOException;
    byte[] getFileByName(String name) throws IOException;
}
