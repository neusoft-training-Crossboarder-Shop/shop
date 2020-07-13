package neu.train.project.order.mapper;

import java.util.List;
import neu.train.project.order.pojo.ProProduct;
import neu.train.project.order.pojo.ProProductExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface ProProductMapper {
    int countByExample(ProProductExample example);

    int deleteByExample(ProProductExample example);

    @Delete({
        "delete from pro_product",
        "where pro_id = #{proId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer proId);

    @Insert({
        "insert into pro_product (pro_id, man_id, ",
        "brd_id, title, sku_cd, ",
        "upc, ean, retail_price, ",
        "mininum_retail_price, warranty_day, ",
        "time_unit, model, ",
        "created_by, create_time, ",
        "last_update_by, last_update_time, ",
        "call_cnt, sts_cd, warranty)",
        "values (#{proId,jdbcType=INTEGER}, #{manId,jdbcType=INTEGER}, ",
        "#{brdId,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, #{skuCd,jdbcType=VARCHAR}, ",
        "#{upc,jdbcType=VARCHAR}, #{ean,jdbcType=VARCHAR}, #{retailPrice,jdbcType=DECIMAL}, ",
        "#{mininumRetailPrice,jdbcType=DECIMAL}, #{warrantyDay,jdbcType=VARCHAR}, ",
        "#{timeUnit,jdbcType=VARCHAR}, #{model,jdbcType=VARCHAR}, ",
        "#{createdBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{lastUpdateBy,jdbcType=VARCHAR}, #{lastUpdateTime,jdbcType=TIMESTAMP}, ",
        "#{callCnt,jdbcType=INTEGER}, #{stsCd,jdbcType=CHAR}, #{warranty,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="proId", before=true, resultType=Integer.class)
    int insert(ProProduct record);

    int insertSelective(ProProduct record);

    List<ProProduct> selectByExampleWithBLOBs(ProProductExample example);

    List<ProProduct> selectByExample(ProProductExample example);

    @Select({
        "select",
        "pro_id, man_id, brd_id, title, sku_cd, upc, ean, retail_price, mininum_retail_price, ",
        "warranty_day, time_unit, model, created_by, create_time, last_update_by, last_update_time, ",
        "call_cnt, sts_cd, warranty",
        "from pro_product",
        "where pro_id = #{proId,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    ProProduct selectByPrimaryKey(Integer proId);

    int updateByExampleSelective(@Param("record") ProProduct record, @Param("example") ProProductExample example);

    int updateByExampleWithBLOBs(@Param("record") ProProduct record, @Param("example") ProProductExample example);

    int updateByExample(@Param("record") ProProduct record, @Param("example") ProProductExample example);

    int updateByPrimaryKeySelective(ProProduct record);

    @Update({
        "update pro_product",
        "set man_id = #{manId,jdbcType=INTEGER},",
          "brd_id = #{brdId,jdbcType=INTEGER},",
          "title = #{title,jdbcType=VARCHAR},",
          "sku_cd = #{skuCd,jdbcType=VARCHAR},",
          "upc = #{upc,jdbcType=VARCHAR},",
          "ean = #{ean,jdbcType=VARCHAR},",
          "retail_price = #{retailPrice,jdbcType=DECIMAL},",
          "mininum_retail_price = #{mininumRetailPrice,jdbcType=DECIMAL},",
          "warranty_day = #{warrantyDay,jdbcType=VARCHAR},",
          "time_unit = #{timeUnit,jdbcType=VARCHAR},",
          "model = #{model,jdbcType=VARCHAR},",
          "created_by = #{createdBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "call_cnt = #{callCnt,jdbcType=INTEGER},",
          "sts_cd = #{stsCd,jdbcType=CHAR},",
          "warranty = #{warranty,jdbcType=LONGVARCHAR}",
        "where pro_id = #{proId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(ProProduct record);

    @Update({
        "update pro_product",
        "set man_id = #{manId,jdbcType=INTEGER},",
          "brd_id = #{brdId,jdbcType=INTEGER},",
          "title = #{title,jdbcType=VARCHAR},",
          "sku_cd = #{skuCd,jdbcType=VARCHAR},",
          "upc = #{upc,jdbcType=VARCHAR},",
          "ean = #{ean,jdbcType=VARCHAR},",
          "retail_price = #{retailPrice,jdbcType=DECIMAL},",
          "mininum_retail_price = #{mininumRetailPrice,jdbcType=DECIMAL},",
          "warranty_day = #{warrantyDay,jdbcType=VARCHAR},",
          "time_unit = #{timeUnit,jdbcType=VARCHAR},",
          "model = #{model,jdbcType=VARCHAR},",
          "created_by = #{createdBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "call_cnt = #{callCnt,jdbcType=INTEGER},",
          "sts_cd = #{stsCd,jdbcType=CHAR}",
        "where pro_id = #{proId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ProProduct record);
}