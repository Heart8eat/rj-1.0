package com.rj.backendjixian.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户浏览商品页面商品简略数据类。
 *
 * @author 毛天航
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodBriefVo {
    @Schema(description = "商品id，用于接下来查询详细信息")
    private String id;

    private Float price;


    private String name;

    @Schema(description = "商品主图片")
    private ImageVo image;


}
