package com.rj.backendjixian.controller;

import com.mybatisflex.core.paginate.Page;
import com.rj.backendjixian.entity.BuyerEntity;
import com.rj.backendjixian.model.Response;
import com.rj.backendjixian.service.IBuyerService;
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
@RequestMapping("/buyers")
@Tag(name = "买家接口")
public class BuyersController {

    @Autowired
    private IBuyerService buyerService;

    /**
     * 添加
     *
     * @param buyer
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加")
    public Response<Boolean> save(@RequestBody BuyerEntity buyer) {
        return Response.success(buyerService.save(buyer));
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
        return Response.success(buyerService.removeById(id));
    }


    /**
     * 根据主键更新
     *
     * @param buyer
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新")
    public Response<Boolean> update(@RequestBody BuyerEntity buyer) {
        return Response.success(buyerService.updateById(buyer));
    }


    /**
     * 查询所有
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有")
    public Response<List<BuyerEntity>> list() {
        return Response.success(buyerService.list());
    }


    /**
     * 根据主键获取详细信息。
     *
     * @param id buyers主键
     * @return 详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"))
    })
    public Response<BuyerEntity> getInfo(@PathVariable Serializable id) {
        return Response.success(buyerService.getById(id));
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
    public Response<Page<BuyerEntity>> page(@RequestParam int pageNumber,
                                            @RequestParam int pageSize) {
        Page<BuyerEntity> page=Page.of(pageNumber,pageSize);
        return Response.success(buyerService.page(page));
    }
}