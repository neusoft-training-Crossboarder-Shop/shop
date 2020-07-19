package neu.train.project.order.mapper;

import java.util.List;
import neu.train.project.order.pojo.StoStoreOrder;
import neu.train.project.order.pojo.StoStoreOrderExample;
import neu.train.project.order.vo.GetAStoOrderQueryByTime;
import neu.train.project.order.vo.SendASimpleSto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface StoStoreOrderMapper {
    int countByExample(StoStoreOrderExample example);

    int deleteByExample(StoStoreOrderExample example);

    @Delete({
        "delete from sto_store_order",
        "where sto_id = #{stoId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer stoId);

    @Insert({
        "insert into sto_store_order (sto_id, proId, ",
        "qty, store_id, purchase_price, ",
        "paid_time, order_status, ",
        "create_time, created_by, ",
        "last_update_by, last_update_time, ",
        "call_cnt, sts_cd)",
        "values (#{stoId,jdbcType=INTEGER}, #{proid,jdbcType=INTEGER}, ",
        "#{qty,jdbcType=INTEGER}, #{storeId,jdbcType=INTEGER}, #{purchasePrice,jdbcType=DECIMAL}, ",
        "#{paidTime,jdbcType=VARCHAR}, #{orderStatus,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{createdBy,jdbcType=VARCHAR}, ",
        "#{lastUpdateBy,jdbcType=VARCHAR}, #{lastUpdateTime,jdbcType=TIMESTAMP}, ",
        "#{callCnt,jdbcType=INTEGER}, #{stsCd,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="stoId", before=false, resultType=Integer.class)
    int insert(StoStoreOrder record);

    int insertSelective(StoStoreOrder record);

    List<StoStoreOrder> selectByExample(StoStoreOrderExample example);

    @Select({
        "select",
        "sto_id, proId, qty, store_id, purchase_price, paid_time, order_status, create_time, ",
        "created_by, last_update_by, last_update_time, call_cnt, sts_cd",
        "from sto_store_order",
        "where sto_id = #{stoId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    StoStoreOrder selectByPrimaryKey(Integer stoId);

    int updateByExampleSelective(@Param("record") StoStoreOrder record, @Param("example") StoStoreOrderExample example);

    int updateByExample(@Param("record") StoStoreOrder record, @Param("example") StoStoreOrderExample example);

    int updateByPrimaryKeySelective(StoStoreOrder record);

    @Update({
        "update sto_store_order",
        "set proId = #{proid,jdbcType=INTEGER},",
          "qty = #{qty,jdbcType=INTEGER},",
          "store_id = #{storeId,jdbcType=INTEGER},",
          "purchase_price = #{purchasePrice,jdbcType=DECIMAL},",
          "paid_time = #{paidTime,jdbcType=VARCHAR},",
          "order_status = #{orderStatus,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "created_by = #{createdBy,jdbcType=VARCHAR},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "call_cnt = #{callCnt,jdbcType=INTEGER},",
          "sts_cd = #{stsCd,jdbcType=CHAR}",
        "where sto_id = #{stoId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(StoStoreOrder record);

    List<SendASimpleSto> selectStoStrProByMany(GetAStoOrderQueryByTime getAStoOrderQueryByTime);
}