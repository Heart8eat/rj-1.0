package com.rj.backendjixian.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantUpdateDto {
    @Schema(description = "新密码")
    @NotBlank
    private String newPassword;
    @Schema(hidden = true)
    private String id;
}
