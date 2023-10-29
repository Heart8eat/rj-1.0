package com.rj.backendjixian.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * 处理图片的工具类
 */
public class FileUtil {
    /**
     * 获取图片宽度
     *
     * @param multipartFile
     * @return
     */
    public static Integer getImageWidth(MultipartFile multipartFile) {
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
     *
     * @param multipartFile
     * @return
     */
    public static Integer getImageHeight(MultipartFile multipartFile) {
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
     *
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }


    /**
     * uuidname
     *
     * @param fileName
     * @return
     */
    public static String getUUIDName(String fileName) {
        String uuid = IdUtil.simpleUUID();
        return uuid + getSuffix(fileName);
    }

    /**
     * 遍历递归删除文件
     *
     * @param dirFile 要被删除的文件或文件夹
     * @return 成功返回true，失败返回true
     */
    public static boolean deleteFile(File dirFile) {
        // 如果dir对应的文件不存在，则退出
        if (!dirFile.exists()) {
            return false;
        }
        // dir是文件，直接删除
        if (dirFile.isFile()) {
            return dirFile.delete();
        } else {

            for (File file : Objects.requireNonNull(dirFile.listFiles())) {
                deleteFile(file);
            }
        }

        return dirFile.delete();
    }

    public static boolean isEqual(MultipartFile f1, File f2) throws IOException {
        return SecureUtil.md5(f1.getInputStream()).equals(SecureUtil.md5(f2));
    }
}
