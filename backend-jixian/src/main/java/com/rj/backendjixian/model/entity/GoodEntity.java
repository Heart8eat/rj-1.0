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
@Schema(name = "GoodEntity")
@Table(value = "goods")
public class GoodEntity {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.uuid)
    private String id;

    @Column(value = "name")
    private String name;

    @Column(value = "quantity")
    private Integer quantity;

    @Column(value = "shop_id")
    private String shopId;

    @Column(value = "status")
    @Schema(description = "0为下架，1为上架 ，2为冻结")
    private Integer status;

    @Column(value = "type_id")
    @Schema(description = "类别id")
    private String typeId;

    @Column(value = "variety")
    @Schema(description = "品种")
    private String variety;

    @Column(value = "weight")
    @Schema(description = "重量")
    private String weight;

    @Column(value = "store")
    @Schema(description = "贮藏方式")
    private String store;

    @Column(value = "shelf_date", onInsertValue = "datetime('now')")
    @Schema(description = "上架时间")
    private String shelfDate;

    @Column(value = "description")
    @Schema(description = "商品描述(可选)")
    private String description;

}
