package com.rj.backendjixian.controller;

import com.mybatisflex.core.paginate.Page;
import com.rj.backendjixian.model.entity.GoodTypeEntity;
import com.rj.backendjixian.model.vo.Response;
import com.rj.backendjixian.service.IGoodTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
@RequestMapping("/goodType")
@Tag(name = "商品类型接口")
public class GoodTypeController {

    @Autowired
    private IGoodTypeService goodTypeService;

    /**
     * 添加
     *
     * @param goodType
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加")
    public Response<Boolean> save(@RequestBody GoodTypeEntity goodType) {
        return Response.success(goodTypeService.save(goodType));
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
            @Parameter(name = "id", description = "", required = true)
    })
    public Response<Boolean> remove(@PathVariable Serializable id) {
        return Response.success(goodTypeService.removeById(id));
    }


    /**
     * 根据主键更新
     *
     * @param goodType
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新")
    public Response<Boolean> update(@RequestBody GoodTypeEntity goodType) {
        return Response.success(goodTypeService.updateById(goodType));
    }


    /**
     * 查询所有
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有")
    public Response<List<GoodTypeEntity>> list() {
        return Response.success(goodTypeService.list());
    }


    /**
     * 根据主键获取详细信息。
     *
     * @param id goodType主键
     * @return 详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true)
    })
    public Response<GoodTypeEntity> getInfo(@PathVariable Serializable id) {
        return Response.success(goodTypeService.getById(id));
    }


    /**
     * 分页查询
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询")
    @Parameters(value = {
            @Parameter(name = "pageNumber", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页大小", required = true)
    })
    public Response<Page<GoodTypeEntity>> page(Page<GoodTypeEntity> page) {
        return Response.success(goodTypeService.page(page));
    }
}