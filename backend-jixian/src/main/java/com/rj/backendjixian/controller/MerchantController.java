package com.rj.backendjixian.controller;

import com.mybatisflex.core.paginate.Page;
import com.rj.backendjixian.model.entity.MerchantEntity;
import com.rj.backendjixian.model.vo.Response;
import com.rj.backendjixian.service.IMerchantService;
import com.rj.backendjixian.util.JwtUtil;
import com.rj.backendjixian.util.LoginToken;
import com.rj.backendjixian.util.PassToken;
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
@RequestMapping("/merchants")
@Tag(name = "商家接口")
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
    @Operation(summary = "添加")
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
    @Operation(summary = "根据主键删除")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"))
    })
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
    @Operation(summary = "根据主键更新")
    public Response<Boolean> update(@RequestBody MerchantEntity merchant) {
        return Response.success(merchantsService.updateById(merchant));
    }


    /**
     * 查询所有
     *
     * @return 所有数据
     */
    @LoginToken
    @GetMapping("/list")
    @Operation(summary = "查询所有")
    public Response<List<MerchantEntity>> list() {
        return Response.success(merchantsService.list());
    }


    /**
     * 根据主键获取详细信息。
     *
     * @param id merchants主键
     * @return 详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"))
    })
    public Response<MerchantEntity> getInfo(@PathVariable Serializable id) {
        return Response.success(merchantsService.getById(id));
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
    public Response<Page<MerchantEntity>> page(@RequestParam int pageNumber,
                                               @RequestParam int pageSize) {
        Page<MerchantEntity> page = Page.of(pageNumber, pageSize);
        return Response.success(merchantsService.page(page));
    }

    /**
     *  卖家登录
     */
    @GetMapping("/login")
    @Operation(summary = "登录")
    @Parameters(value = {
            @Parameter(name = "name", description = "名字", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "password", description = "密码", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    @PassToken
    public Object login(@RequestParam(value = "name")String name,
                        @RequestParam(value = "password")String password){
        MerchantEntity merchantEntity = merchantsService.login(name, password);

        String jwtToken = JwtUtil.createToken(merchantEntity);

        return jwtToken;
    }
}