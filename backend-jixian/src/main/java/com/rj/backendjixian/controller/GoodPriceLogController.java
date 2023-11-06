package com.rj.backendjixian.controller;

import com.mybatisflex.core.paginate.Page;
import com.rj.backendjixian.model.dto.GoodPriceLogDto;
import com.rj.backendjixian.model.entity.GoodPriceLogEntity;
import com.rj.backendjixian.model.vo.GoodPriceLogVo;
import com.rj.backendjixian.model.vo.Response;
import com.rj.backendjixian.service.GoodPriceLogService;
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
@RequestMapping("/goodPriceLog")
@Tag(name = "商品价格管理接口")
public class GoodPriceLogController {

    @Autowired
    private GoodPriceLogService goodPriceLogService;

    /**
     * 更改价格
     *
     * @param goodPriceLog
     * @return {@code true} 更改成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "更改价格")
    public Response<Boolean> save(@RequestBody GoodPriceLogDto goodPriceLog) {
        return Response.success(goodPriceLogService.saveLog(goodPriceLog));
    }


    /**
     * 查询商品所有历史价格
     *
     * @return 所有历史记录
     */
    @GetMapping("/getGoodPriceLogVoList/{good_id}")
    @Operation(summary = "查询商品所有历史价格")
    public Response<List<GoodPriceLogVo>> getGoodPriceLogVoList(@PathVariable Serializable good_id) {
        return Response.success(goodPriceLogService.getGoodPriceLogVoList(good_id.toString()));
    }


    /**
     * 根据主键获取详细信息。
     *
     * @param id goodPriceLog主键
     * @return 详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据主键获取详细信息", hidden = true)
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
    @Operation(summary = "分页查询", hidden = true)
    @Parameters(value = {
            @Parameter(name = "pageNumber", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页大小", required = true)
    })
    public Response<Page<GoodPriceLogEntity>> page(Page<GoodPriceLogEntity> page) {
        return Response.success(goodPriceLogService.page(page));
    }
}