package com.rj.backendjixian.controller;

import com.rj.backendjixian.model.vo.ImageVo;
import com.rj.backendjixian.model.vo.Response;
import com.rj.backendjixian.service.IGoodService;
import com.rj.backendjixian.service.IImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/files")
@Tag(name = "图片接口")
@CrossOrigin
public class FileController {
    @Autowired
    IImageService imageService;

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
          @Parameter(name = "id", description = "前端传图片的uuid", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    public Response<List<ImageVo>> uploadImg(@RequestParam Serializable id, @RequestPart("mainFile") MultipartFile mainFile,
                                             @RequestPart("uploadFiles[]") MultipartFile[] files) throws IOException {
        //用于返回的list<ImageVo>
        List<ImageVo> root = new ArrayList<ImageVo>();

        return Response.success(imageService.uploadImgs(files, mainFile, id.toString(), root));
    }


    /**
     * 删除文件夹接口
     *
     * @param uuid
     * @return
     */
    @PostMapping(value = "/deleteImg")
    @Operation(summary = "删除文件")
    @Parameters(value = {
            @Parameter(name = "uuid", description = "图片文件夹uuid", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    public Response<Boolean> deleteImg(@RequestParam Serializable uuid) {
        return Response.success((imageService.deleteImgs(uuid.toString())));
    }

}
