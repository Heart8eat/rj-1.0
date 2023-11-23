package com.rj.backendjixian.model.dto;

import com.rj.backendjixian.model.entity.BuyerEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerCreateDto {

    @NotBlank(message = "名字为必填项")
    @Schema(example = "Tom")
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

    public BuyerEntity dto2Entity() {
        return BuyerEntity.builder()
                .name(this.name)
                .build();
    }
}
