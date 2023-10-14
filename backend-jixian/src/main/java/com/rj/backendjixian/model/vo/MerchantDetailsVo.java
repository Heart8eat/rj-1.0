package com.rj.backendjixian.model.vo;

import com.rj.backendjixian.model.entity.MerchantEntity;
import com.rj.backendjixian.model.entity.ShopEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDetailsVo {


    private String name;

    private String password;

    private String phone;
    //暂时使用shop_id
    private ShopEntity shop;

    public MerchantDetailsVo(MerchantEntity merchant,ShopEntity shop) {
        this.name = merchant.getName();
        this.password=merchant.getPassword();
        this.phone=merchant.getPhone();
        this.shop=shop;
    }
}
