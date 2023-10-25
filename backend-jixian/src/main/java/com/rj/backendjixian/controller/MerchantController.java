package com.rj.backendjixian.controller;

import com.mybatisflex.core.paginate.Page;
import com.rj.backendjixian.exception.LoginException;
import com.rj.backendjixian.model.dto.MerchantUpdateDto;
import com.rj.backendjixian.model.entity.MerchantEntity;
import com.rj.backendjixian.model.entity.ShopEntity;
import com.rj.backendjixian.model.vo.MerchantDetailsVo;
import com.rj.backendjixian.model.vo.Response;
import com.rj.backendjixian.model.vo.TokenVo;
import com.rj.backendjixian.service.IMerchantService;
import com.rj.backendjixian.util.Context;
import com.rj.backendjixian.util.JwtUtil;
import com.rj.backendjixian.util.LoginToken;
import com.rj.backendjixian.util.PassToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 控制层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@RestController
@RequestMapping("/merchants")
@Tag(name = "商家接口")
@CrossOrigin
public class MerchantController {

    @Autowired
    private IMerchantService merchantsService;

    /**
     * 添加
     *
     * @param merchant
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加",hidden = true)
    public Response<Boolean> save(@RequestBody MerchantEntity merchant) {
        return Response.success(merchantsService.save(merchant));
    }


    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "根据主键删除",hidden = true)
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"))
    })
    @LoginToken
    @SecurityRequirement(name = "token")
    public Response<Boolean> remove(@PathVariable Serializable id) {
        return Response.success(merchantsService.removeById(id));
    }


    /**
     * 根据主键更新
     *
     * @param merchant
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新",hidden = true)
    @LoginToken
    @SecurityRequirement(name = "token")
    public Response<Boolean> update(@RequestBody MerchantEntity merchant) {
        return Response.success(merchantsService.updateById(merchant));
    }


    /**
     * 查询所有
     *
     * @return 所有数据
     */
    @LoginToken
    @SecurityRequirement(name = "token")
    @GetMapping("/list")
    @Operation(summary = "查询所有", hidden = true)
    public Response<List<MerchantEntity>> list() {
        return Response.success(merchantsService.list());
    }


    /**
     * 获取登录商家的详细信息。
     *
     * @return 详情
     *
     */
    @LoginToken
    @SecurityRequirement(name = "token")
    @GetMapping("/getInfo")
    @Operation(summary = "获取登录商家的详细信息")
    public Response<MerchantDetailsVo> getInfo() {
        MerchantEntity merchant= (MerchantEntity) Context.get("merchant");
        ShopEntity shop=(ShopEntity) Context.get("shop");
        return Response.success(new MerchantDetailsVo(merchant,shop));
    }


    /**
     * 分页查询
     *
     * @param pageNumber 页码
     * @param pageSize   每页大小
     * @return 分页对象
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询", hidden = true)
    @Parameters(value = {
            @Parameter(name = "pageNumber", description = "页码", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer")),
            @Parameter(name = "pageSize", description = "每页大小", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
    })
    @LoginToken
    @SecurityRequirement(name = "token")
    public Response<Page<MerchantEntity>> page(@RequestParam int pageNumber,
                                               @RequestParam int pageSize) {
        Page<MerchantEntity> page = Page.of(pageNumber, pageSize);
        return Response.success(merchantsService.page(page));
    }

    /**
     * 卖家登录
     * @param name
     * @param password
     * @return
     */
    @GetMapping("/login")
    @Operation(summary = "登录")
    @Parameters(value = {
            @Parameter(name = "name", description = "名字", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "password", description = "密码", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    @PassToken
    public Response<TokenVo> login(@RequestParam(value = "name")String name,
                                   @RequestParam(value = "password")String password) throws LoginException {
        MerchantEntity merchantEntity = merchantsService.login(name, password);

        return Response.success(new TokenVo(JwtUtil.createToken(merchantEntity),"Bearer"));
    }

    /**
     * 更改商家密码
     * @param merchantUpdateDto
     * @return
     */
    @PutMapping("/updatePassword")
    @Operation(summary = "更改密码")
    @SecurityRequirement(name = "token")
    @LoginToken
    public Response<Boolean> updatePassword(@Valid @RequestBody MerchantUpdateDto merchantUpdateDto){
        MerchantEntity merchant=(MerchantEntity) Context.get("merchant");
        merchantUpdateDto.setId(merchant.getId());
        return Response.success((merchantsService.updatePassword(merchantUpdateDto)));
    }
}