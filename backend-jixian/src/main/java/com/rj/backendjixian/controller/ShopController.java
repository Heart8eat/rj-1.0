package com.rj.backendjixian.controller;

import com.mybatisflex.core.paginate.Page;
import com.rj.backendjixian.entity.ShopEntity;
import com.rj.backendjixian.model.Response;
import com.rj.backendjixian.service.IShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping("/shops")
@Tag(name = "商铺接口")
public class ShopController {

    @Autowired
    private IShopService shopsService;

    /**
     * 添加
     *
     * @param shop
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加")
    public Response<Boolean> save(@RequestBody ShopEntity shop) {
        return Response.success(shopsService.save(shop));
    }


    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "根据主键删除")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"))
    })
    public Response<Boolean> remove(@PathVariable Serializable id) {
        return Response.success(shopsService.removeById(id));
    }


    /**
     * 根据主键更新
     *
     * @param shop
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新")
    public Response<Boolean> update(@RequestBody ShopEntity shop) {
        return Response.success(shopsService.updateById(shop));
    }


    /**
     * 查询所有
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有")
    public Response<List<ShopEntity>> list() {
        return Response.success(shopsService.list());
    }


    /**
     * 根据主键获取详细信息。
     *
     * @param id shops主键
     * @return 详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"))
    })
    public Response<ShopEntity> getInfo(@PathVariable Serializable id) {
        return Response.success(shopsService.getById(id));
    }


    /**
     * 分页查询
     *
     * @param pageNumber 页码
     * @param pageSize 每页大小
     * @return 分页对象
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询")
    @Parameters(value = {
            @Parameter(name = "pageNumber", description = "页码", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer")),
            @Parameter(name = "pageSize", description = "每页大小", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
    })
    public Response<Page<ShopEntity>> page(@RequestParam int pageNumber,
                                           @RequestParam int pageSize) {
        Page<ShopEntity> page=Page.of(pageNumber,pageSize);
        return Response.success(shopsService.page(page));
    }
}