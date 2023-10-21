package com.rj.backendjixian.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "goodOrder")
@Table(value = "goods_orders")
public class GoodOrderEntity {
    @Id(keyType = KeyType.None)
    @Column(value = "good_id")
    private String goodId;

    @Id(keyType = KeyType.None)
    @Column(value = "order_id")
    private String orderId;

    @Column(value = "quantity")
    private Integer quantity;

    @Column(value = "sum")
    private Float sum;


}
