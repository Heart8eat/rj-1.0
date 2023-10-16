package com.rj.backendjixian.controller;

import com.mybatisflex.core.paginate.Page;
import com.rj.backendjixian.model.dto.GoodOrderDto;
import com.rj.backendjixian.model.entity.*;
import com.rj.backendjixian.model.vo.HistoryOrderVo;
import com.rj.backendjixian.model.vo.Response;
import com.rj.backendjixian.service.IGoodOrderService;
import com.rj.backendjixian.service.IOrderService;
import com.rj.backendjixian.util.Context;
import com.rj.backendjixian.util.LoginToken;
import com.rj.backendjixian.util.PassToken;
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

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 控制层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@RestController
@RequestMapping("/orders")
@Tag(name = "订单接口")
@SecurityRequirement(name = "token")
public class OrderController {

    @Autowired
    private IOrderService ordersService;

    /**
     * 添加
     *
     * @param order
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加")
    @LoginToken
    public Response<Boolean> save(@RequestBody OrderEntity order) {
        return Response.success(ordersService.save(order));
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
    @LoginToken
    public Response<Boolean> remove(@PathVariable Serializable id) {
        return Response.success(ordersService.removeById(id));
    }


    /**
     * 根据主键更新
     *
     * @param order
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新")
    @LoginToken
    public Response<Boolean> update(@RequestBody OrderEntity order) {
        return Response.success(ordersService.updateById(order));
    }


    /**
     * 查询所有
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有")
    @LoginToken
    public Response<List<OrderEntity>> list() {
        return Response.success(ordersService.list());
    }


    /**
     * 根据主键获取详细信息。
     *
     * @param id orders主键
     * @return 详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"))
    })
    @LoginToken
    public Response<OrderEntity> getInfo(@PathVariable Serializable id) {
        return Response.success(ordersService.getById(id));
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
    @LoginToken
    public Response<Page<OrderEntity>> page(@RequestParam int pageNumber,
                                            @RequestParam int pageSize) {
        Page<OrderEntity> page = Page.of(pageNumber, pageSize);
        return Response.success(ordersService.page(page));
    }


    /**
     *  获取历史订单
     *
     * @param id
     * @return
     */
    @RequestMapping("/HistoryOrders/{id}")
    @Operation(summary = "根据商铺ID获取历史订单")
    @Parameters(value = {
            @Parameter(name = "id", description = "商铺id", required = true, in = ParameterIn.PATH, schema = @Schema(type = "String"))
    })
    @PassToken
    public List<HistoryOrderVo> HistoryOrders(@PathVariable Serializable id){
        List<HistoryOrderVo> list = ordersService.getHistoryOrders(id.toString());

        return list;
    }

    /**
     * 根据id更改订单状态
     * @param status
     * @param id
     * @return
     */
    @PutMapping("/UpdateStatus")
    @Operation(summary = "更改状态")
    @SecurityRequirement(name = "token")
    @LoginToken
    @Parameters(value = {
            @Parameter(name = "status", description = "订单状态", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer")),
            @Parameter(name = "id", description = "订单id", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    public Response<Integer> updateStatus(@RequestParam int status,@RequestParam String id){
        OrderEntity orderEntity = ordersService.getById(id) ;
        orderEntity.setStatus(status);

        return Response.success((ordersService.update(orderEntity)));
    }

    @Autowired
    IGoodOrderService goodOrderService;

    @PostMapping("/newOrder")
    @Operation(summary = "生成订单")
    @SecurityRequirement(name = "token")
    @LoginToken
    public Response<Boolean> newOrder(@RequestBody GoodOrderDto goodOrderDto){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setStatus(0);
        orderEntity.setBuyerId(goodOrderDto.getBuyerId());
        orderEntity.setShopId(goodOrderDto.getShopId());

        GoodOrderEntity goodOrderEntity = new GoodOrderEntity();
        goodOrderEntity.setOrderId(orderEntity.getId());
        goodOrderEntity.setGoodId(goodOrderDto.getGoodId());
        goodOrderEntity.setQuantity(goodOrderDto.getQuantity());
        goodOrderEntity.setSum(goodOrderDto.getSum());

        return Response.success(ordersService.save(orderEntity) && goodOrderService.save(goodOrderEntity));
    }

}