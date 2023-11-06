package com.rj.backendjixian.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data

public class HistoryGoodVo extends BasicGoodVo {


    @Schema(description = "0为下架，1为上架 ，2为冻结")
    private Integer status;


}
