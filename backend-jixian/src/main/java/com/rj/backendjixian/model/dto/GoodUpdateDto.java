package com.rj.backendjixian.model.dto;

import cn.hutool.core.bean.BeanUtil;
import com.rj.backendjixian.model.entity.GoodEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodUpdateDto {
    private String id;

    private Float price;

    private String name;

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
    private String fakeId;
    public GoodEntity dto2Entity() {
        return BeanUtil.copyProperties(this,GoodEntity.class,"type","price","variety","fakeId");
    }
}
