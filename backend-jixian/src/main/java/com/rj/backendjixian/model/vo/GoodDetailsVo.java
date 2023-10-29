package com.rj.backendjixian.model.vo;


import com.rj.backendjixian.model.entity.GoodEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 商品详情页面数据类
 *
 * @author 毛天航
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodDetailsVo {
    private String id;
    private Float price;

    private String name;
    @Schema(description = "主图片")
    private ImageVo mainImage;

    @Schema(description = "其他图片")
    private List<ImageVo> image;
//    库存暂时没有
//    private Integer quantity;

    @Schema(description = "类别")
    private String type;


    @Schema(description = "品种")
    private String variety;


    @Schema(description = "重量")
    private String weight;


    @Schema(description = "贮藏方式")
    private String store;


    @Schema(description = "商品描述(可选)")
    private String description;


    public GoodDetailsVo(GoodEntity goodEntity) {
        this.store = goodEntity.getStore();
        this.price = goodEntity.getPrice();
        this.variety = goodEntity.getVariety();
        this.name = goodEntity.getName();
        this.weight = goodEntity.getWeight();
        this.description = goodEntity.getDescription();
    }
}
