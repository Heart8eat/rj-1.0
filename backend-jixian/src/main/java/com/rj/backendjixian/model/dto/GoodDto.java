package com.rj.backendjixian.model.dto;

import com.mybatisflex.annotation.Column;
import com.rj.backendjixian.model.entity.GoodEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "GoodDto")
public class GoodDto {

    private Float price;

    private String name;

    @Schema(description = "商品照片")
    private MultipartFile[] images;

    @Schema(description = "商品库存")
    private Integer quantity;

    private String shopId;

    @Schema(description = "0为下架，1为上架 ，2为冻结")
    private Integer status;
}
