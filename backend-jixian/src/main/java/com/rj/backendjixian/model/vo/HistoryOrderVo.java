package com.rj.backendjixian.model.vo;

import com.rj.backendjixian.controller.BuyersController;
import com.rj.backendjixian.model.entity.BuyerEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryOrderVo {
    @Schema(description = "订单ID")
    private String order_id;
    @Schema(description = "商品名字")
    private String name;

    @Schema(description = "订单状态")
    private String status;

    @Schema(description = "图片")
    private String image;

    @Schema(description = "支付方式")
    private String pay = "手动支付";

    @Schema(description = "买家ID")
    private String buyer_id;

    @Schema(description = "买家")
    private BuyerEntity buyer;

}
