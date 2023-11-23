package com.rj.backendjixian.model.vo;


import com.rj.backendjixian.model.entity.GoodEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 商品详情页面数据类
 *
 * @author 毛天航
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodDetailsVo extends BaseGoodVo{

    private Float price;

    @Schema(description = "其他图片")
    private List<ImageVo> images;


    @Schema(description = "商品描述(可选)")
    private String description;

}
