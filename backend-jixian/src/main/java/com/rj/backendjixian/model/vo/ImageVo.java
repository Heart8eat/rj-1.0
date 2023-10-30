package com.rj.backendjixian.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ImageVo {

    //0-正常  1-失败
    @Schema(description = "0-正常  1-失败")
    private Integer statusCode;

    @Schema(description = "图片地址")
    private String url;

    @Schema(description = "图片宽度")
    private Integer width;

    @Schema(description = "图片高度")
    private Integer height;

    /**
     * 失败ImageVo创建
     *
     * @return ImageVo
     */
    public static ImageVo fail() {
        return new ImageVo(1, null, null, null);
    }

    /**
     * 成功ImageVo创建
     *
     * @param url    图片地址
     * @param width  图片宽度
     * @param height 图片高度
     * @return ImageVo
     */
    public static ImageVo success(String url, Integer width, Integer height) {
        return new ImageVo(0, url, width, height);
    }
}
