package com.rj.backendjixian.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodOrderDto {
    @Schema(description = "商铺ID")
    private String shopId;
    @Schema(description = "商品ID")
    private String goodId;
    @Schema(description = "买家ID")
    private String buyerId;
    @Schema(description = "数量")
    private int quantity;
    @Schema(description = "总价")
    private float sum;
}
