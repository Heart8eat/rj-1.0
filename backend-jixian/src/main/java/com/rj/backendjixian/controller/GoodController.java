package com.rj.backendjixian.controller;

import com.mybatisflex.core.paginate.Page;
import com.rj.backendjixian.model.dto.GoodCreateDto;
import com.rj.backendjixian.model.dto.PublishGoodDto;
import com.rj.backendjixian.model.entity.GoodEntity;
import com.rj.backendjixian.model.entity.ShopEntity;
import com.rj.backendjixian.model.vo.*;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/goods")
@Tag(name = "商品接口")
@CrossOrigin
public class GoodController {

    @Autowired
    private IGoodService goodService;


    /**
     * 创建商品
     *
     * @param good
     * @return {@code true} 创建成功，{@code false} 创建失败
     */
    @PostMapping(value = "/create")
    @Operation(summary = "创建商品")
    @LoginToken
    @SecurityRequirement(name = "token")
    public Response<Map<String, String>> create(@RequestBody GoodCreateDto good) {
        return Response.success(goodService.createGood(good));
    }
    /*
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "创建商品")
    @LoginToken
    @SecurityRequirement(name = "token")
    public Response<List<ImageVo>> create(GoodCreateDto good,
                                          @RequestPart("file") MultipartFile[] files) throws IOException {
        String id = goodService.createGood(good).get("goodId");
        return Response.success(goodService.uploadImgs(files, id));
    }
*/

    /**
     * 批量发布商品接口
     *
     * @return ture false
     */
    @LoginToken
    @SecurityRequirement(name = "token")
    @PutMapping("/publish")
    @Operation(summary = "批量发布商品")
    public Response<Boolean> publish(@RequestBody PublishGoodDto publishGoodDto) {
        return Response.success(goodService.publish(publishGoodDto));
    }

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @LoginToken
    @SecurityRequirement(name = "token")
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "根据主键删除", hidden = true)
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
    @SecurityRequirement(name = "token")
    @PutMapping("/update")
    @Operation(summary = "根据主键更新", hidden = true)
    public Response<Boolean> update(@RequestBody GoodEntity good) {
        return Response.success(goodService.updateById(good));
    }


    /**
     * 查询所有上架商品的简略信息
     *
     * @return 所有上架商品
     */

    @GetMapping("/getGoodBriefList")
    @Operation(summary = "查询所有上架商品的简略信息")
    public Response<List<GoodBriefVo>> getGoodBriefList(@RequestParam(required = false) String type, @RequestParam(required = false) String name) {
        return Response.success(goodService.getGoodBriefList(type, name));
    }

    /**
     * 查询历史商品页面接口
     *
     * @return 所有历史商品
     */
    @LoginToken
    @SecurityRequirement(name = "token")
    @GetMapping("/getHistoryGoodList")
    @Operation(summary = "查询所有历史商品")
//    @Parameters(value = {
//            @Parameter(name = "shop_id", description = "商铺id", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"))
//    })
    public Response<List<HistoryGoodVo>> getHistoryGoodList(@RequestParam(required = false) String type, @RequestParam(required = false) String name) {
        ShopEntity shop = (ShopEntity) Context.get("shop");
        String shop_id = shop.getId();
        return Response.success(goodService.getHistoryGoodList(shop_id, type, name));
    }

    /**
     * 更改商品状态接口
     *
     * @return ture false
     */
    @LoginToken
    @SecurityRequirement(name = "token")
    @PutMapping("/changeGoodStatue/{id}")
    @Operation(summary = " 更改商品状态")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string")),
            @Parameter(name = "statue", description = "", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
    })
    public Response<Boolean> freeze(@PathVariable Serializable id, @RequestParam int statue) {
        return Response.success(goodService.changeGoodStatue((String) id, statue));
    }


    /**
     * 根据主键获取商品详细信息。
     *
     * @param id goods主键
     * @return 详情
     */
    @GetMapping("/getGoodDetails/{id}")
    @Operation(summary = "根据主键获取商品详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"))
    })
    public Response<GoodDetailsVo> getInfo(@PathVariable Serializable id) {
        return Response.success(goodService.getGoodDetails(id.toString()));
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
    public Response<Page<GoodEntity>> page(@RequestParam int pageNumber,
                                           @RequestParam int pageSize) {
        Page<GoodEntity> page = Page.of(pageNumber, pageSize);
        return Response.success(goodService.page(page));
    }


    /**
     * 多图片上传测试接口
     *
     * @param id
     * @param files
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/uploadImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "保存图片")
    @Parameters(value = {
//            @Parameter(name = "imgFile", description = "图片", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "file")),
            @Parameter(name = "id", description = "前端生成的id", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "main", description = "1为主图片，0为其他图片", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
    })
    public Response<List<ImageVo>> uploadImg(@RequestParam Serializable id, @RequestParam Integer main,
                                             @RequestPart("uploadFiles") MultipartFile[] files) throws IOException {
        return Response.success(goodService.uploadImgs(files, id.toString(), main != null ? main : 0));
    }


    @PostMapping(value = "/deleteImg")
    @Operation(summary = "删除文件")
    @Parameters(value = {
            @Parameter(name = "uuid", description = "图片文件夹uuid", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    public Response<Boolean> deleteImg(@RequestParam Serializable uuid) {
        return Response.success((goodService.deleteImgs(uuid.toString())));
    }
}