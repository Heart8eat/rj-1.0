package com.rj.backendjixian.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

/**
 * 处理图片的工具类
 */
public class FileUtil {
    /**
     * 获取图片宽度
     * @param multipartFile
     * @return
     */
    public static Integer getImageWidth(MultipartFile multipartFile){
        try {
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            return bufferedImage.getWidth();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取图片高度
     * @param multipartFile
     * @return
     */
    public static Integer getImageHeight(MultipartFile multipartFile){
        try {
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            return bufferedImage.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据文件全名获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * uuidname
     * @param fileName
     * @return
     */
    public static String getUUIDName(String fileName){
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid + getSuffix(fileName);
    }
}
