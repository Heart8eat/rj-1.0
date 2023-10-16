package com.rj.backendjixian.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodOrderDto {
    private String shopId;
    private String goodId;
    private String buyerId;

    private int quantity;
    private float sum;
}
