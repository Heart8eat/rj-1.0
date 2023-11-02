package com.rj.backendjixian.model.entity;

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
@Schema(name = "good_price_log")
@Table(value = "good_price_log")
public class GoodPriceLogEntity {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.uuid)
    private String id;

    @Column(value = "good_id")
    private String goodId;

    @Column(value = "price")
    private String price;

    @Column(value = "create_time")
    private String createTime;


}
