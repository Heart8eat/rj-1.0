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
@Schema(name = "good")
@Table(value = "goods")
public class GoodEntity {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.uuid)
    private String id;

    @Column(value = "price")
    private Float price;

    @Column(value = "name")
    private String name;

    @Column(value = "image")
    private String image;

    @Column(value = "quantity")
    private Integer quantity;

    @Column(value = "shop_id")
    private String shopId;

    @Column(value = "status")
    @Schema(description = "0为下架，1为上架 ，2为冻结")
    private Integer status;


}
