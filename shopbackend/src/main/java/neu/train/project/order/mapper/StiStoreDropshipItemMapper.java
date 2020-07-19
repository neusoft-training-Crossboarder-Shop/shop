package neu.train.project.order.mapper;

import java.util.List;
import neu.train.project.order.pojo.StiStoreDropshipItem;
import neu.train.project.order.pojo.StiStoreDropshipItemExample;
import neu.train.project.order.vo.GetStiQuery;
import neu.train.project.order.vo.SendSti;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component(value = "DDMapper")
public interface StiStoreDropshipItemMapper {
    int countByExample(StiStoreDropshipItemExample example);

    int deleteByExample(StiStoreDropshipItemExample example);

    @Delete({
        "delete from sti_store_dropship_item",
        "where dil_id = #{dilId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer dilId);

    @Insert({
        "insert into sti_store_dropship_item (dil_id, pro_id, ",
        "str_id, sal_price, ",
        "dropship_price, store_retention_amount, ",
        "shelf_stock_amount, dropship_status, ",
        "created_by, create_time, ",
        "call_cnt, remark, ",
        "sts_cd, last_update_by, ",
        "last_update_time)",
        "values (#{dilId,jdbcType=INTEGER}, #{proId,jdbcType=INTEGER}, ",
        "#{strId,jdbcType=INTEGER}, #{salPrice,jdbcType=DECIMAL}, ",
        "#{dropshipPrice,jdbcType=DECIMAL}, #{storeRetentionAmount,jdbcType=INTEGER}, ",
        "#{shelfStockAmount,jdbcType=INTEGER}, #{dropshipStatus,jdbcType=VARCHAR}, ",
        "#{createdBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{callCnt,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR}, ",
        "#{stsCd,jdbcType=CHAR}, #{lastUpdateBy,jdbcType=VARCHAR}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="dilId", before=false, resultType=Integer.class)
    int insert(StiStoreDropshipItem record);

    int insertSelective(StiStoreDropshipItem record);

    List<StiStoreDropshipItem> selectByExample(StiStoreDropshipItemExample example);

    @Select({
        "select",
        "dil_id, pro_id, str_id, sal_price, dropship_price, store_retention_amount, shelf_stock_amount, ",
        "dropship_status, created_by, create_time, call_cnt, remark, sts_cd, last_update_by, ",
        "last_update_time",
        "from sti_store_dropship_item",
        "where dil_id = #{dilId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    StiStoreDropshipItem selectByPrimaryKey(Integer dilId);

    int updateByExampleSelective(@Param("record") StiStoreDropshipItem record, @Param("example") StiStoreDropshipItemExample example);

    int updateByExample(@Param("record") StiStoreDropshipItem record, @Param("example") StiStoreDropshipItemExample example);

    int updateByPrimaryKeySelective(StiStoreDropshipItem record);

    @Update({
        "update sti_store_dropship_item",
        "set pro_id = #{proId,jdbcType=INTEGER},",
          "str_id = #{strId,jdbcType=INTEGER},",
          "sal_price = #{salPrice,jdbcType=DECIMAL},",
          "dropship_price = #{dropshipPrice,jdbcType=DECIMAL},",
          "store_retention_amount = #{storeRetentionAmount,jdbcType=INTEGER},",
          "shelf_stock_amount = #{shelfStockAmount,jdbcType=INTEGER},",
          "dropship_status = #{dropshipStatus,jdbcType=VARCHAR},",
          "created_by = #{createdBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "call_cnt = #{callCnt,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "sts_cd = #{stsCd,jdbcType=CHAR},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP}",
        "where dil_id = #{dilId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(StiStoreDropshipItem record);

    List<SendSti> selectSTIByMany(GetStiQuery getStiQuery);
}