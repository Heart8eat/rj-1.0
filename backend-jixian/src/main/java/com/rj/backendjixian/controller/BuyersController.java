package com.rj.backendjixian.controller;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.mybatisflex.core.paginate.Page;
import com.rj.backendjixian.annotation.LoginToken;
import com.rj.backendjixian.annotation.PassToken;
import com.rj.backendjixian.annotation.RequiresRoles;
import com.rj.backendjixian.annotation.Role;
import com.rj.backendjixian.exception.LoginException;
import com.rj.backendjixian.model.dto.BuyerCreateDto;
import com.rj.backendjixian.model.entity.BuyerAddressEntity;
import com.rj.backendjixian.model.entity.BuyerEntity;
import com.rj.backendjixian.model.vo.CaptchaVo;
import com.rj.backendjixian.model.vo.Response;
import com.rj.backendjixian.model.vo.TokenVo;
import com.rj.backendjixian.service.IBuyerAddressService;
import com.rj.backendjixian.service.IBuyerService;
import com.rj.backendjixian.util.Context;
import com.rj.backendjixian.util.JwtUtil;
import com.wf.captcha.SpecCaptcha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 控制层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@RestController
@RequestMapping("/buyers")
@Tag(name = "买家接口")
@CrossOrigin
@Slf4j
public class BuyersController {

    @Autowired
    private IBuyerService buyerService;
    @Autowired
    private TimedCache<String,Object> timedCache;
    @Autowired
    private IBuyerAddressService buyerAddressService;

    /**
     * 添加
     *
     * @param buyer
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/create")
    @Operation(summary = "添加买家信息")
    public Response<Map<String, String>> create(@Validated @RequestBody BuyerCreateDto buyer) {
        return Response.success(buyerService.createBuyer(buyer));
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
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"))
    })
    @LoginToken
    @SecurityRequirement(name = "token")
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
    @Operation(summary = "根据主键更新", hidden = true)
    @LoginToken
    @SecurityRequirement(name = "token")
    public Response<Boolean> update(@RequestBody BuyerEntity buyer) {
        return Response.success(buyerService.updateById(buyer));
    }


    /**
     * 查询所有
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有", hidden = true)
    @LoginToken
    @SecurityRequirement(name = "token")
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
    @Operation(summary = "根据主键获取详细信息", hidden = true)
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"))
    })
//    @LoginToken
    public Response<BuyerEntity> getInfo(@PathVariable Serializable id) {
        return Response.success(buyerService.getById(id));
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
    @LoginToken
    @SecurityRequirement(name = "token")
    public Response<Page<BuyerEntity>> page(@RequestParam int pageNumber,
                                            @RequestParam int pageSize) {
        Page<BuyerEntity> page = Page.of(pageNumber, pageSize);
        return Response.success(buyerService.page(page));
    }


    /**
     * 查询所有地址
     *
     * @return 所有数据
     */
    @GetMapping("/listAddress")
    @Operation(summary = "查询某个买家的所有地址")
    @LoginToken
    @SecurityRequirement(name = "token")
    @Parameters(value = {
            @Parameter(name = "buyerId", description = "买家id", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    public Response<List<BuyerAddressEntity>> listAddressByBuyerId(@RequestParam String buyerId) {
        return Response.success(buyerAddressService.selectByBuyerId(buyerId));
    }


    /**
     * 添加地址
     *
     * @param buyerAddress
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/saveAddress")
    @Operation(summary = "添加地址")
    @LoginToken
    @SecurityRequirement(name = "token")
    public Response<Boolean> saveAddress(@RequestBody BuyerAddressEntity buyerAddress) {
        return Response.success(buyerAddressService.save(buyerAddress));
    }

    /**
     * 买家登录
     *
     * @param name
     * @param password
     * @return
     */
    @GetMapping("/login")
    @Operation(summary = "登录")
    @Parameters(value = {
            @Parameter(name = "name", description = "名字", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "password", description = "密码", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    @PassToken
    public Response<TokenVo> login(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "password") String password) throws LoginException {

        BuyerEntity buyerEntity = buyerService.login(name, password);

        return Response.success(new TokenVo(JwtUtil.createToken(buyerEntity), "Bearer"));
    }

    @SecurityRequirement(name = "token")
    @GetMapping("/getInfo")
    @Operation(summary = "获取登录买家的详细信息")
    @LoginToken
    @RequiresRoles(roles = Role.BUYER)
    public Response<BuyerEntity> getInfo() {
        BuyerEntity buyer = (BuyerEntity) Context.get("buyer");
        return Response.success(buyer);
    }

    /**
     * 生成图片验证码
     *
     */
    @GetMapping("/getCode")
    @Operation(summary = "图片验证码（验证码会在30分钟后过期）")
    @PassToken
    public Response<CaptchaVo> getCode(){
//         英文与数字验证码
        SpecCaptcha captcha = new SpecCaptcha(120, 40);
        // 几位数运算
        captcha.setLen(4);
        // 获取运算的结果
        String result = captcha.text().toLowerCase();

        // 生成id标识验证码
        String key= IdUtil.simpleUUID();
        // 放入全局timeout缓存中 30分钟后过期
        timedCache.put(key,result,30*60*1000);
        log.info("验证码:{}",result);
        return Response.success(CaptchaVo.builder()
                .key(key)
                .image(captcha.toBase64())
                .build());
    }

    /**
     * 添加买家
     * @param buyerCreateDto
     * @return
     */
    @PostMapping("/newBuyer")
    @Operation(summary = "添加买家(验证码)")
    @PassToken
    public Response<Boolean> newMerchant(@RequestBody BuyerCreateDto buyerCreateDto) {

        if(!buyerCreateDto.getVerify().trim().toLowerCase()
                .equals(timedCache.get(buyerCreateDto.getKey()))){
            log.info("验证码错误");
            return Response.success(Boolean.FALSE);
        }

        if (!buyerCreateDto.checkPassword()) {
            log.info("两次密码不同");
            return Response.success(Boolean.FALSE);
        }


        BuyerEntity buyerEntity = new BuyerEntity();
        buyerEntity.setName(buyerCreateDto.getName());
        String hashedPassword= BCrypt.hashpw(buyerCreateDto.getPwd1(), BCrypt.gensalt());
        buyerEntity.setPassword(hashedPassword);
        return Response.success(buyerService.save(buyerEntity));
    }

}