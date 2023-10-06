package com.rj.backendjixian.controller;

import com.mybatisflex.core.paginate.Page;
import com.rj.backendjixian.model.dto.GoodDto;
import com.rj.backendjixian.model.vo.Response;
import com.rj.backendjixian.model.entity.GoodEntity;
import com.rj.backendjixian.service.IFileService;
import com.rj.backendjixian.service.IGoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;


@RestController
@RequestMapping("/goods")
@Tag(name = "商品接口")
public class GoodController {

    @Autowired
    private IGoodService goodService;
    @Autowired
    private IFileService fileService;
    /**
     * 添加
     * @param imageName
     * @return 字节流
     */
    @GetMapping(path = "/image/{imageName}",produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @Operation(summary = "请求商品图片")
    public byte[] getImageByName(@PathVariable String imageName) throws IOException {
      return fileService.getFileByName(imageName);
    }
    /**
     * 添加
     * @param good
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {@Content(
            mediaType = "multipart/form-data",
            schema = @Schema(implementation = GoodDto.class))})
    public Response<Boolean> save(GoodDto good) throws IOException {
        List<String> fileNames=fileService.batchUpload(good.getImages());
        return Response.success(goodService.save(GoodEntity.builder().
                shopId(good.getShopId()).price(good.getPrice()).
                image(String.join(",", fileNames)).
                quantity(good.getQuantity()).name(good.getName()).
                status(good.getStatus()).build()));
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
        return Response.success(goodService.removeById(id));
    }


    /**
     * 根据主键更新
     *
     * @param good
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新")
    public Response<Boolean> update(@RequestBody GoodEntity good) {
        return Response.success(goodService.updateById(good));
    }


    /**
     * 查询所有
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有")
    public Response<List<GoodEntity>> list() {
        return Response.success(goodService.list());
    }


    /**
     * 根据主键获取详细信息。
     *
     * @param id goods主键
     * @return 详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"))
    })
    public Response<GoodEntity> getInfo(@PathVariable Serializable id) {
        return Response.success(goodService.getById(id));
    }


    /**
     * 分页查询
     *
     * @param pageNumber 页码
     * @param pageSize   每页大小
     * @return 分页对象
     */
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