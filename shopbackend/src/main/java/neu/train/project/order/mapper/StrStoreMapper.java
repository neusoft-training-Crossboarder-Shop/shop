package neu.train.project.order.mapper;

import java.util.List;
import neu.train.project.order.pojo.StrStore;
import neu.train.project.order.pojo.StrStoreExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface StrStoreMapper {
    int countByExample(StrStoreExample example);

    int deleteByExample(StrStoreExample example);

    @Delete({
        "delete from str_store",
        "where store_id = #{storeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer storeId);

    @Insert({
        "insert into str_store (store_id, dsr_id, ",
        "platform_type, store_name, ",
        "authencation_code, created_by, ",
        "create_time, last_update_by, ",
        "last_update_time, call_cnt, ",
        "sts_cd)",
        "values (#{storeId,jdbcType=INTEGER}, #{dsrId,jdbcType=INTEGER}, ",
        "#{platformType,jdbcType=INTEGER}, #{storeName,jdbcType=VARCHAR}, ",
        "#{authencationCode,jdbcType=VARCHAR}, #{createdBy,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastUpdateBy,jdbcType=VARCHAR}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP}, #{callCnt,jdbcType=INTEGER}, ",
        "#{stsCd,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="storeId", before=true, resultType=Integer.class)
    int insert(StrStore record);

    int insertSelective(StrStore record);

    List<StrStore> selectByExample(StrStoreExample example);

    @Select({
        "select",
        "store_id, dsr_id, platform_type, store_name, authencation_code, created_by, ",
        "create_time, last_update_by, last_update_time, call_cnt, sts_cd",
        "from str_store",
        "where store_id = #{storeId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    StrStore selectByPrimaryKey(Integer storeId);

    int updateByExampleSelective(@Param("record") StrStore record, @Param("example") StrStoreExample example);

    int updateByExample(@Param("record") StrStore record, @Param("example") StrStoreExample example);

    int updateByPrimaryKeySelective(StrStore record);

    @Update({
        "update str_store",
        "set dsr_id = #{dsrId,jdbcType=INTEGER},",
          "platform_type = #{platformType,jdbcType=INTEGER},",
          "store_name = #{storeName,jdbcType=VARCHAR},",
          "authencation_code = #{authencationCode,jdbcType=VARCHAR},",
          "created_by = #{createdBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "call_cnt = #{callCnt,jdbcType=INTEGER},",
          "sts_cd = #{stsCd,jdbcType=CHAR}",
        "where store_id = #{storeId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(StrStore record);
}