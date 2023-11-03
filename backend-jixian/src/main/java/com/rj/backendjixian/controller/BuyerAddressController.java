package com.rj.backendjixian.controller;

import com.mybatisflex.core.paginate.Page;
import com.rj.backendjixian.model.entity.BuyerAddressEntity;
import com.rj.backendjixian.model.vo.Response;
import com.rj.backendjixian.service.IBuyerAddressService;
import com.rj.backendjixian.util.LoginToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/buyerAddress")
@Tag(name = "地址接口")
@CrossOrigin
public class BuyerAddressController {

    @Autowired
    private IBuyerAddressService buyerAddressService;

    /**
     * 添加
     *
     * @param buyerAddress
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加")
    @LoginToken
    @SecurityRequirement(name = "token")
    public Response<Boolean> save(@RequestBody BuyerAddressEntity buyerAddress) {
        return Response.success(buyerAddressService.save(buyerAddress));
    }


    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "根据主键删除", hidden = true)
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true)
    })
    public Response<Boolean> remove(@PathVariable Serializable id) {
        return Response.success(buyerAddressService.removeById(id));
    }


    /**
     * 根据主键更新
     *
     * @param buyerAddress
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新", hidden = true)
    public Response<Boolean> update(@RequestBody BuyerAddressEntity buyerAddress) {
        return Response.success(buyerAddressService.updateById(buyerAddress));
    }


    /**
     * 查询所有
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有地址")
    @LoginToken
    @SecurityRequirement(name = "token")
    public Response<List<BuyerAddressEntity>> list() {
        return Response.success(buyerAddressService.list());
    }


    /**
     * 根据主键获取详细信息。
     *
     * @param id buyerAddress主键
     * @return 详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据主键获取详细信息", hidden = true)
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true)
    })
    public Response<BuyerAddressEntity> getInfo(@PathVariable Serializable id) {
        return Response.success(buyerAddressService.getById(id));
    }


    /**
     * 分页查询
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询", hidden = true)
    @Parameters(value = {
            @Parameter(name = "pageNumber", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页大小", required = true)
    })
    public Response<Page<BuyerAddressEntity>> page(Page<BuyerAddressEntity> page) {
        return Response.success(buyerAddressService.page(page));
    }
}