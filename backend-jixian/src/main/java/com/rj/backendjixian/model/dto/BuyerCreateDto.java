package com.rj.backendjixian.model.dto;

import com.rj.backendjixian.model.entity.BuyerEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerCreateDto {

    @NotBlank(message = "名字为必填项")
    @Schema(example = "Tom")
    private String name;


    @Schema(example = "杭州")
    private String address;


    @Schema(example = "14816714403")
    @Length(min = 11, max = 11, message = "电话的长度为11")
    private String phone;

    @Schema(example = "123456789@qq.com")
    @Email
    private String email;
    public BuyerEntity dto2Entity(){
        return BuyerEntity.builder()
                .email(this.email)
                .address(this.address)
                .name(this.name)
                .phone(this.phone)
                .build();
    }
}
