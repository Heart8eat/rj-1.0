package com.rj.backendjixian.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodPriceLogDto {
    @Schema(description = "商品id")
    private String goodId;

    @Schema(description = "变动价格")
    private Float newPrice;
}
