package com.rj.backendjixian.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
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
@Schema(name = "商品订单关联")
@Table(value = "goods_orders")
public class GoodOrderEntity {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.uuid)
    private String goodId;

    @Id(keyType = KeyType.Generator, value = KeyGenerators.uuid)
    private String orderId;

    @Column(value = "quantity")
    private Integer quantity;

    @Column(value = "sum")
    private Float sum;


}
