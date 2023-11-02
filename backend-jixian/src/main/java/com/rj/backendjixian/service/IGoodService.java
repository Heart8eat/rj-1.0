package com.rj.backendjixian.service;


import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import com.rj.backendjixian.model.dto.GoodCreateDto;
import com.rj.backendjixian.model.dto.PublishGoodDto;
import com.rj.backendjixian.model.entity.GoodEntity;
import com.rj.backendjixian.model.vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface IGoodService extends IService<GoodEntity> {
    List<HistoryGoodVo> getHistoryGoodList(String shop_id, String type, String name);
    Page<HistoryGoodVo> getHistoryGoodPage(Page<HistoryGoodVo> page,String shop_id, String type, String name);
    List<PublishGoodVo> getPublishGoodList(String shop_id, String type, String name);
    Page<PublishGoodVo> getPublishGoodPage(Page<PublishGoodVo> page,String shop_id, String type, String name);

    List<GoodBriefVo> getGoodBriefList(String type, String name);
    Page<GoodBriefVo> getGoodBriefPage(Page<GoodBriefVo> page,String type, String name);

    boolean deleteImgs(String id);

    List<ImageVo> uploadImgs(MultipartFile[] multipartFiles, String id, Integer main) throws IOException;

    Map<String, String> createGood(GoodCreateDto good);

    Boolean changeGoodStatue(String id, int statue);

    GoodDetailsVo getGoodDetails(String id);

    boolean publish(PublishGoodDto publishGoodDto);

}