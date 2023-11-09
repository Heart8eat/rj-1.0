package com.rj.backendjixian.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaptchaVo {
    @Schema(description = "后端标识验证码")
    private String key;
    @Schema(description = "验证码图片（base64）")
    private String image;
}
