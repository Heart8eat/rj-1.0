package com.rj.backendjixian.controller;

import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.rj.backendjixian.service.GoodPriceLogService;
import com.rj.backendjixian.model.entity.GoodPriceLogEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

import com.rj.backendjixian.model.vo.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 控制层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@RestController
@RequestMapping("/goodPriceLog")
@Tag(name = "控制层")
public class GoodPriceLogController {

    @Autowired
    private GoodPriceLogService goodPriceLogService;

    /**
     * 添加
     *
     * @param goodPriceLog
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加")
    public Response<Boolean> save(@RequestBody GoodPriceLogEntity goodPriceLog) {
        return Response.success(goodPriceLogService.save(goodPriceLog));
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
        return Response.success(goodPriceLogService.removeById(id));
    }


    /**
     * 根据主键更新
     *
     * @param goodPriceLog
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新")
    public Response<Boolean> update(@RequestBody GoodPriceLogEntity goodPriceLog) {
        return Response.success(goodPriceLogService.updateById(goodPriceLog));
    }


    /**
     * 查询所有
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有")
    public Response<List<GoodPriceLogEntity>> list() {
        return Response.success(goodPriceLogService.list());
    }


    /**
     * 根据主键获取详细信息。
     *
     * @param id goodPriceLog主键
     * @return 详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true)
    })
    public Response<GoodPriceLogEntity> getInfo(@PathVariable Serializable id) {
        return Response.success(goodPriceLogService.getById(id));
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
    public Response<Page<GoodPriceLogEntity>> page(Page<GoodPriceLogEntity> page) {
        return Response.success(goodPriceLogService.page(page));
    }
}