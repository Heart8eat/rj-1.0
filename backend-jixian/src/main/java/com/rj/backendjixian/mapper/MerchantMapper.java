package com.rj.backendjixian.mapper;

import com.mybatisflex.core.BaseMapper;
import com.rj.backendjixian.model.entity.MerchantEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 映射层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Mapper
public interface MerchantMapper extends BaseMapper<MerchantEntity> {
    @Select("select * from merchants where name = #{name} and password = #{password}")
    MerchantEntity login(@Param("name")String name, @Param("password")String password);


}
