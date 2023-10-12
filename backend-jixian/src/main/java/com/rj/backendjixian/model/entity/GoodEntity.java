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

    @Column(value = "type")
    @Schema(description = "类别")
    private String type;

    @Column(value = "variety")
    @Schema(description = "品种")
    private String variety;

    @Column(value = "weight")
    @Schema(description = "重量")
    private String weight;

    @Column(value = "store")
    @Schema(description = "贮藏方式")
    private String store;

    @Column(value = "source")
    @Schema(description = "产地")
    private String source;

    @Column(value = "entrance")
    @Schema(description = "进口/国产")
    private String entrance;

    @Column(value = "shelf_date")
    @Schema(description = "上架时间")
    private String shelfDate;

}
