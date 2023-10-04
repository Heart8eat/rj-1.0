package com.rj.backendjixian.model.vo;

import com.rj.backendjixian.model.entity.GoodEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "OrderVo")
public class OrderVo {
    private String id;
    private Integer status;

    private String createTime;

    private String finishTime;
    @Schema(description = "订单总价")
    private float totalPrice;
    private List<GoodEntity> goods;

}
