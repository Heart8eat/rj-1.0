package com.rj.backendjixian.model.dto;

import com.rj.backendjixian.model.entity.GoodEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodCreateDto {
    private String shopId;

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
    public GoodEntity dto2Entity(){
        GoodEntity goodEntity=new GoodEntity();
        goodEntity.setImage(String.join(",",this.image));
        goodEntity.setName(this.name);
        goodEntity.setEntrance(this.entrance);
        goodEntity.setPrice(this.price);
        goodEntity.setQuantity(this.quantity);
        goodEntity.setSource(this.source);
        goodEntity.setQuantity(this.quantity);
        goodEntity.setShelfDate(this.shelfDate);
        goodEntity.setStore(this.store);
        goodEntity.setVariety(this.variety);
        goodEntity.setType(this.type);
        goodEntity.setShopId(this.shopId);
        goodEntity.setWeight(this.weight);
        goodEntity.setStatus(1);
        return goodEntity;
    }
}
