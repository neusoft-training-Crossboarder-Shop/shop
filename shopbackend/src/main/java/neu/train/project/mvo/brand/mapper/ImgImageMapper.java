package neu.train.project.mvo.brand.mapper;

import java.util.List;
import neu.train.project.mvo.brand.pojo.ImgImage;
import neu.train.project.mvo.brand.pojo.ImgImageExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ImgImageMapper {
    int countByExample(ImgImageExample example);

    int deleteByExample(ImgImageExample example);

    @Delete({
        "delete from img_image",
        "where img_id = #{imgId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer imgId);

    @Insert({
        "insert into img_image (img_id, uri, ",
        "type_cd, pro_id, created_by, ",
        "create_time, last_update_by, ",
        "last_update_time, call_cnt, ",
        "sts_cd)",
        "values (#{imgId,jdbcType=INTEGER}, #{uri,jdbcType=VARCHAR}, ",
        "#{typeCd,jdbcType=CHAR}, #{proId,jdbcType=INTEGER}, #{createdBy,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastUpdateBy,jdbcType=VARCHAR}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP}, #{callCnt,jdbcType=INTEGER}, ",
        "#{stsCd,jdbcType=CHAR})"
    })
    int insert(ImgImage record);

    int insertSelective(ImgImage record);

    List<ImgImage> selectByExample(ImgImageExample example);

    @Select({
        "select",
        "img_id, uri, type_cd, pro_id, created_by, create_time, last_update_by, last_update_time, ",
        "call_cnt, sts_cd",
        "from img_image",
        "where img_id = #{imgId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    ImgImage selectByPrimaryKey(Integer imgId);

    int updateByExampleSelective(@Param("record") ImgImage record, @Param("example") ImgImageExample example);

    int updateByExample(@Param("record") ImgImage record, @Param("example") ImgImageExample example);

    int updateByPrimaryKeySelective(ImgImage record);

    @Update({
        "update img_image",
        "set uri = #{uri,jdbcType=VARCHAR},",
          "type_cd = #{typeCd,jdbcType=CHAR},",
          "pro_id = #{proId,jdbcType=INTEGER},",
          "created_by = #{createdBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "call_cnt = #{callCnt,jdbcType=INTEGER},",
          "sts_cd = #{stsCd,jdbcType=CHAR}",
        "where img_id = #{imgId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ImgImage record);
}