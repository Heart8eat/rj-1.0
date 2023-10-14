package com.rj.backendjixian.model.vo;

import cn.hutool.core.util.StrUtil;
import com.rj.backendjixian.model.entity.GoodEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryGoodVo {
    private String id;

    private String name;

    private String image;


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

    public HistoryGoodVo(GoodEntity goodEntity) {
        this.id=goodEntity.getId();
        this.name = goodEntity.getName();
        this.image = StrUtil.split(goodEntity.getImage(), ",").get(0);
        this.type=goodEntity.getType();
        this.variety=goodEntity.getVariety();
        this.weight=goodEntity.getWeight();
        this.store=goodEntity.getStore();
        this.shelfDate=goodEntity.getShelfDate();
    }
}
