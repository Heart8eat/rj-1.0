package com.rj.backendjixian.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodPriceLogVo {

    @Schema(description = "变动价格")
    private Float price;
    @Schema(description = "更改时间")
    private String create_time;
}
