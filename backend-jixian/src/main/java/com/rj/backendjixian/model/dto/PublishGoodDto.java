package com.rj.backendjixian.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class PublishGoodDto {
    @Schema(description = "要发布的商品Id")
    private List<String> ids;
}
