package neu.train.project.mvo.brand.mapper;

import java.util.List;
import neu.train.project.mvo.brand.pojo.ManManufacturer;
import neu.train.project.mvo.brand.pojo.ManManufacturerExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ManManufacturerMapper {
    int countByExample(ManManufacturerExample example);

    int deleteByExample(ManManufacturerExample example);

    @Delete({
        "delete from man_manufacturer",
        "where man_id = #{manId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer manId);

    @Insert({
        "insert into man_manufacturer (man_id, sys_user_id, ",
        "name_en, name_cn, ",
        "gmc_report_type, gmc_report_url, ",
        "created_by, create_time, ",
        "last_update_by, last_update_time, ",
        "call_cnt, sts_cd, description)",
        "values (#{manId,jdbcType=INTEGER}, #{sysUserId,jdbcType=INTEGER}, ",
        "#{nameEn,jdbcType=VARCHAR}, #{nameCn,jdbcType=VARCHAR}, ",
        "#{gmcReportType,jdbcType=VARCHAR}, #{gmcReportUrl,jdbcType=VARCHAR}, ",
        "#{createdBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{lastUpdateBy,jdbcType=VARCHAR}, #{lastUpdateTime,jdbcType=TIMESTAMP}, ",
        "#{callCnt,jdbcType=INTEGER}, #{stsCd,jdbcType=CHAR}, #{description,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="manId", before=true, resultType=Integer.class)
    int insert(ManManufacturer record);

    int insertSelective(ManManufacturer record);

    List<ManManufacturer> selectByExampleWithBLOBs(ManManufacturerExample example);

    List<ManManufacturer> selectByExample(ManManufacturerExample example);

    @Select({
        "select",
        "man_id, sys_user_id, name_en, name_cn, gmc_report_type, gmc_report_url, created_by, ",
        "create_time, last_update_by, last_update_time, call_cnt, sts_cd, description",
        "from man_manufacturer",
        "where man_id = #{manId,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    ManManufacturer selectByPrimaryKey(Integer manId);

    int updateByExampleSelective(@Param("record") ManManufacturer record, @Param("example") ManManufacturerExample example);

    int updateByExampleWithBLOBs(@Param("record") ManManufacturer record, @Param("example") ManManufacturerExample example);

    int updateByExample(@Param("record") ManManufacturer record, @Param("example") ManManufacturerExample example);

    int updateByPrimaryKeySelective(ManManufacturer record);

    @Update({
        "update man_manufacturer",
        "set sys_user_id = #{sysUserId,jdbcType=INTEGER},",
          "name_en = #{nameEn,jdbcType=VARCHAR},",
          "name_cn = #{nameCn,jdbcType=VARCHAR},",
          "gmc_report_type = #{gmcReportType,jdbcType=VARCHAR},",
          "gmc_report_url = #{gmcReportUrl,jdbcType=VARCHAR},",
          "created_by = #{createdBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "call_cnt = #{callCnt,jdbcType=INTEGER},",
          "sts_cd = #{stsCd,jdbcType=CHAR},",
          "description = #{description,jdbcType=LONGVARCHAR}",
        "where man_id = #{manId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(ManManufacturer record);

    @Update({
        "update man_manufacturer",
        "set sys_user_id = #{sysUserId,jdbcType=INTEGER},",
          "name_en = #{nameEn,jdbcType=VARCHAR},",
          "name_cn = #{nameCn,jdbcType=VARCHAR},",
          "gmc_report_type = #{gmcReportType,jdbcType=VARCHAR},",
          "gmc_report_url = #{gmcReportUrl,jdbcType=VARCHAR},",
          "created_by = #{createdBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "call_cnt = #{callCnt,jdbcType=INTEGER},",
          "sts_cd = #{stsCd,jdbcType=CHAR}",
        "where man_id = #{manId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ManManufacturer record);
}