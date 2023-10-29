package com.rj.backendjixian.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
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
@Schema(name = "日志实体类")

public class WebLogEntity {
    @Id(keyType = KeyType.Generator, value = KeyGenerators.uuid)
    private String id;
    @Column(value = "summary")
    private String summary;
    @Column(value = "description")
    private String description;

    @Column(value = "start_time")
    private String startTime;

    @Column(value = "spend_time")
    private String spendTime;

    @Column(value = "base_path")
    private String basePath;

    @Column(value = "uri")
    private String uri;

    @Column(value = "url")
    private String url;

    @Column(value = "method")
    private String method;

    @Column(value = "ip")
    private String ip;

    @Column(value = "parameter")
    private String parameter;

    @Column(value = "result")
    private String result;


}
