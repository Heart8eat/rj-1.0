package com.rj.backendjixian.model.vo;

import com.rj.backendjixian.model.entity.GoodEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

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
    private Float price;

    private String name;

    private List<String> image;

    private Integer quantity;

    @Schema(description = "类别")
    private String type;


    @Schema(description = "品种")
    private String variety;


    @Schema(description = "重量")
    private String weight;


    @Schema(description = "贮藏方式")
    private String store;


    @Schema(description = "产地")
    private String source;


    @Schema(description = "进口/国产")
    private String entrance;

    @Schema(description = "上架日期")
    private String shelfDate;

    public GoodDetailsVo(GoodEntity goodEntity) {
        this.entrance=goodEntity.getEntrance();
        this.shelfDate=goodEntity.getShelfDate();
        this.store=goodEntity.getStore();
        this.price=goodEntity.getPrice();
        this.variety=goodEntity.getVariety();
        this.image= List.of(StringUtils.split(goodEntity.getImage(), ","));
        this.name=goodEntity.getName();
        this.quantity=goodEntity.getQuantity();
        this.type=goodEntity.getType();
        this.weight=goodEntity.getWeight();
    }
}
