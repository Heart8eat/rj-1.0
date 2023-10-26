package com.rj.backendjixian.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ImageVo {
    /**
     * 0-正常  1-失败
     */
    private Integer statusCode;
    /**
     * 图片地址
     */
    private String url;
    /**
     * 图片宽度
     */
    private Integer width;
    /**
     * 图片高度
     */
    private Integer height;

    /**
     * 失败
     * @return ImageVo
     */
    public static ImageVo fail(){
        return new ImageVo(1,null,null,null);
    }

    /**
     * 成功
     * @param url 图片地址
     * @param width 图片宽度
     * @param height 图片高度
     * @return ImageVo
     */
    public static ImageVo success(String url,Integer width,Integer height){
        return new ImageVo(0,url,width,height);
    }
}
