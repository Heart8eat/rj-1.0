package com.rj.backendjixian.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishGoodVo {
    private String id;

    private String name;

    private ImageVo image;


    @Schema(description = "类别")
    private String type;


    @Schema(description = "品种")
    private String variety;


    @Schema(description = "重量")
    private String weight;


    @Schema(description = "贮藏方式")
    private String store;


    @Schema(description = "上架日期")
    private String shelfDate;

    @Schema(description = "商品价格")
    private Float price;
}
