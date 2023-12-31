package com.rj.backendjixian.model.dto;


import cn.hutool.core.bean.BeanUtil;
import com.rj.backendjixian.model.entity.GoodEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodCreateDto {
    @NotBlank(message = "商铺id不能为空")
    private String shopId;

    private Float price;

    private String name;


    private Integer quantity;

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

    @Schema(description = "前端生成的id")
    private String fakeId;

    public GoodEntity dto2Entity() {
        GoodEntity goodEntity = BeanUtil.copyProperties(this,GoodEntity.class,"fakeId","quantity","type","price");
        goodEntity.setQuantity(this.quantity != null ? this.quantity : 1);
        goodEntity.setStatus(0);
        return goodEntity;
    }
}
