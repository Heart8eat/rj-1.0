package com.rj.backendjixian.model.entity;

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

import java.util.Date;

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
@Schema(name = "order")
@Table(value = "orders")
public class OrderEntity {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.uuid)
    private String id;

    @Column(value = "status")
    @Schema(description = "0为 ，1为 ，2为 ")
    private Integer status;

    @Column(value = "create_time", onInsertValue = "datetime('now','localtime')")
    private String createTime;

    @Column(value = "finish_time")
    private String finishTime;

    @Column(value = "buyer_id")
    private String buyerId;

    @Column(value = "shop_id")
    private String shopId;


}
