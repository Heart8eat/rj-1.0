package com.rj.backendjixian.entity;

import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.lang.String;
import java.lang.Integer;

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
@Schema(name = "订单")
@Table(value = "orders")
public class OrderEntity {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.uuid)
    private String id;

    @Column(value = "status")
    private Integer status;

    @Column(value = "create_time")
    private String createTime;

    @Column(value = "finish_time")
    private String finishTime;

    @Column(value = "buyer_id")
    private String buyerId;

    @Column(value = "shop_id")
    private String shopId;


}
