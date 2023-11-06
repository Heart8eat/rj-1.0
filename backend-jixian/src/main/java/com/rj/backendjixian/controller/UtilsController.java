package com.rj.backendjixian.controller;

import cn.hutool.crypto.digest.BCrypt;
import com.rj.backendjixian.model.vo.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Tag(name = "调试用接口")
@CrossOrigin
@RequestMapping("/utils")
public class UtilsController {
    @GetMapping("/hashedPassword")
    @Operation(summary = "获取hash后的密码,可用于手动向数据库添加密码")
    @Parameters(value = {
            @Parameter(name = "password", description = "原密码", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    public Response<String> hashedPassword(@RequestParam String password){
        String hashedPassword=BCrypt.hashpw(password, BCrypt.gensalt());
        return Response.success(hashedPassword);
    }
}
