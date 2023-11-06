package com.rj.backendjixian.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantCreateDto {
    @Schema(description = "名字")
    private String name;
    @Schema(description = "验证码")
    private String verify;
    @Schema(description = "标识验证码的uuid")
    private String key;
    @Schema(description = "密码")
    private String pwd1;
    @Schema(description = "确认密码")
    private String pwd2;
    public boolean checkPassword(){
        return this.pwd1.equals(this.pwd2);
    }

}
