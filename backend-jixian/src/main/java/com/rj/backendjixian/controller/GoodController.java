package com.rj.backendjixian.controller;

import com.mybatisflex.core.paginate.Page;
import com.rj.backendjixian.model.dto.GoodCreateDto;
import com.rj.backendjixian.model.entity.GoodEntity;
import com.rj.backendjixian.model.entity.ShopEntity;
import com.rj.backendjixian.model.vo.GoodBriefVo;
import com.rj.backendjixian.model.vo.GoodDetailsVo;
import com.rj.backendjixian.model.vo.HistoryGoodVo;
import com.rj.backendjixian.model.vo.Response;
import com.rj.backendjixian.service.IGoodService;
import com.rj.backendjixian.util.Context;
import com.rj.backendjixian.util.LoginToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

import static com.rj.backendjixian.model.entity.table.GoodEntityTableDef.GOOD_ENTITY;


@RestController
@RequestMapping("/goods")
@Tag(name = "商品接口")
@SecurityRequirement(name = "token")
public class GoodController {

    @Autowired
    private IGoodService goodService;


    /**
     * 添加
     *
     * @param good
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加")
    public Response<Boolean> save(@RequestBody GoodEntity good) {
        return Response.success(goodService.save(good));
    }

    /**
     * 创建商品
     *
     * @param good
     * @return {@code true} 创建成功，{@code false} 创建失败
     */
    @LoginToken
    @PostMapping("/create")
    @Operation(summary = "创建商品")
    public Response<Boolean> create(@RequestBody GoodCreateDto good) {
        return Response.success(goodService.save(good.dto2Entity()));
    }

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @LoginToken
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "根据主键删除")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"))
    })
    public Response<Boolean> remove(@PathVariable Serializable id) {
        return Response.success(goodService.removeById(id));
    }


    /**
     * 根据主键更新
     *
     * @param good
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @LoginToken
    @PutMapping("/update")
    @Operation(summary = "根据主键更新")
    public Response<Boolean> update(@RequestBody GoodEntity good) {
        return Response.success(goodService.updateById(good));
    }


    /**
     * 查询所有上架商品的简略信息
     *
     * @return 所有上架商品
     */
    @LoginToken
    @GetMapping("/getGoodBriefList")
    @Operation(summary = "查询所有上架商品的简略信息")
    public Response<List<GoodBriefVo>> getGoodBriefList() {
        return Response.success(goodService.getGoodBriefList());
    }
    /**
     * 查询历史商品页面接口
     * @return 所有历史商品
     */
    @LoginToken
    @SecurityRequirement(name = "token")
    @GetMapping("/getHistoryGoodList")
    @Operation(summary = "查询所有历史商品")
//    @Parameters(value = {
//            @Parameter(name = "shop_id", description = "商铺id", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"))
//    })
    public Response<List<HistoryGoodVo>> getHistoryGoodList() {
        ShopEntity shop= (ShopEntity) Context.get("shop");
        String shop_id=shop.getId();
        return Response.success(goodService.getHistoryGoodList(shop_id));
    }
    /**
     * 更改商品状态接口
     * @return ture false
     */
    @LoginToken
    @PutMapping("/changeGoodStatue/{id}")
    @Operation(summary = " 更改商品状态")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string")),
            @Parameter(name = "statue", description = "", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
    })
    public Response<Boolean> freeze(@PathVariable Serializable id,@RequestParam int statue) {
        return Response.success(goodService.changeGoodStatue((String) id,statue));
    }


    /**
     * 根据主键获取商品详细信息。
     *
     * @param id goods主键
     * @return 详情
     */
    @LoginToken
    @GetMapping("/getGoodDetails/{id}")
    @Operation(summary = "根据主键获取商品详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"))
    })
    public Response<GoodDetailsVo> getInfo(@PathVariable Serializable id) {
        return Response.success(new GoodDetailsVo(goodService.getById(id)));
    }


    /**
     * 分页查询
     *
     * @param pageNumber 页码
     * @param pageSize   每页大小
     * @return 分页对象
     */
    @LoginToken
    @GetMapping("/page")
    @Operation(summary = "分页查询")
    @Parameters(value = {
            @Parameter(name = "pageNumber", description = "页码", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer")),
            @Parameter(name = "pageSize", description = "每页大小", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
    })
    public Response<Page<GoodEntity>> page(@RequestParam int pageNumber,
                                           @RequestParam int pageSize) {
        Page<GoodEntity> page = Page.of(pageNumber, pageSize);
        return Response.success(goodService.page(page));
    }
}