package com.rj.backendjixian.model.vo;

import com.mybatisflex.annotation.RelationManyToMany;
import com.mybatisflex.annotation.RelationManyToOne;
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
@Schema(description = "OrderVo")
public class OrderVo {
    private String id;
    private Integer status;

    private String createTime;

    private String finishTime;
    @Schema(description = "订单总价")
    private float totalPrice;
    @RelationManyToMany(joinTable = "goods_orders",
                        joinSelfColumn = "order_id",
                        joinTargetColumn = "good_id")
    private List<GoodEntity> goods;

}
