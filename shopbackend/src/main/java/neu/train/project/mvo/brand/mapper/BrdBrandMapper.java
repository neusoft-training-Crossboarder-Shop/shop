package neu.train.project.mvo.brand.mapper;

import java.util.List;
import neu.train.project.mvo.brand.pojo.BrdBrand;
import neu.train.project.mvo.brand.pojo.BrdBrandExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface BrdBrandMapper {
    int countByExample(BrdBrandExample example);

    int deleteByExample(BrdBrandExample example);

    @Delete({
        "delete from brd_brand",
        "where brd_id = #{brdId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer brdId);

    @Insert({
        "insert into brd_brand (brd_id, man_id, ",
        "name_en, name_cn, ",
        "img_id, created_by, ",
        "create_time, last_update_by, ",
        "last_update_time, call_cnt, ",
        "sts_cd)",
        "values (#{brdId,jdbcType=INTEGER}, #{manId,jdbcType=INTEGER}, ",
        "#{nameEn,jdbcType=VARCHAR}, #{nameCn,jdbcType=VARCHAR}, ",
        "#{imgId,jdbcType=INTEGER}, #{createdBy,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastUpdateBy,jdbcType=VARCHAR}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP}, #{callCnt,jdbcType=INTEGER}, ",
        "#{stsCd,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="manId", before=true, resultType=Integer.class)
    int insert(BrdBrand record);

    int insertSelective(BrdBrand record);

    List<BrdBrand> selectByExample(BrdBrandExample example);

    @Select({
        "select",
        "brd_id, man_id, name_en, name_cn, img_id, created_by, create_time, last_update_by, ",
        "last_update_time, call_cnt, sts_cd",
        "from brd_brand",
        "where brd_id = #{brdId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    BrdBrand selectByPrimaryKey(Integer brdId);

    int updateByExampleSelective(@Param("record") BrdBrand record, @Param("example") BrdBrandExample example);

    int updateByExample(@Param("record") BrdBrand record, @Param("example") BrdBrandExample example);

    int updateByPrimaryKeySelective(BrdBrand record);

    @Update({
        "update brd_brand",
        "set man_id = #{manId,jdbcType=INTEGER},",
          "name_en = #{nameEn,jdbcType=VARCHAR},",
          "name_cn = #{nameCn,jdbcType=VARCHAR},",
          "img_id = #{imgId,jdbcType=INTEGER},",
          "created_by = #{createdBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "call_cnt = #{callCnt,jdbcType=INTEGER},",
          "sts_cd = #{stsCd,jdbcType=CHAR}",
        "where brd_id = #{brdId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BrdBrand record);
}