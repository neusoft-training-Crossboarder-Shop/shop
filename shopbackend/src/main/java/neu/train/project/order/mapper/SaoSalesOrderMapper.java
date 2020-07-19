package neu.train.project.order.mapper;

import java.util.List;
import neu.train.project.order.pojo.SaoSalesOrder;
import neu.train.project.order.pojo.SaoSalesOrderExample;
import neu.train.project.order.vo.GetASaoQueryByTime;
import neu.train.project.order.vo.SendComplexSao;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface SaoSalesOrderMapper {
    int countByExample(SaoSalesOrderExample example);

    int deleteByExample(SaoSalesOrderExample example);

    @Delete({
        "delete from sao_sales_order",
        "where sales_oder_id = #{salesOderId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer salesOderId);

    @Insert({
        "insert into sao_sales_order (sales_oder_id, man_id, ",
        "proId, qty, price, ",
        "sto_id, purchase_price, ",
        "tracking_no, created_by, ",
        "create_time, last_update_by, ",
        "last_update_time, call_cnt, ",
        "sts_cd)",
        "values (#{salesOderId,jdbcType=INTEGER}, #{manId,jdbcType=INTEGER}, ",
        "#{proid,jdbcType=INTEGER}, #{qty,jdbcType=INTEGER}, #{price,jdbcType=DECIMAL}, ",
        "#{stoId,jdbcType=INTEGER}, #{purchasePrice,jdbcType=DECIMAL}, ",
        "#{trackingNo,jdbcType=VARCHAR}, #{createdBy,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastUpdateBy,jdbcType=VARCHAR}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP}, #{callCnt,jdbcType=INTEGER}, ",
        "#{stsCd,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="salesOderId", before=false, resultType=Integer.class)
    int insert(SaoSalesOrder record);

    int insertSelective(SaoSalesOrder record);

    List<SaoSalesOrder> selectByExample(SaoSalesOrderExample example);

    @Select({
        "select",
        "sales_oder_id, man_id, proId, qty, price, sto_id, purchase_price, tracking_no, ",
        "created_by, create_time, last_update_by, last_update_time, call_cnt, sts_cd",
        "from sao_sales_order",
        "where sales_oder_id = #{salesOderId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SaoSalesOrder selectByPrimaryKey(Integer salesOderId);

    int updateByExampleSelective(@Param("record") SaoSalesOrder record, @Param("example") SaoSalesOrderExample example);

    int updateByExample(@Param("record") SaoSalesOrder record, @Param("example") SaoSalesOrderExample example);

    int updateByPrimaryKeySelective(SaoSalesOrder record);

    @Update({
        "update sao_sales_order",
        "set man_id = #{manId,jdbcType=INTEGER},",
          "proId = #{proid,jdbcType=INTEGER},",
          "qty = #{qty,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "sto_id = #{stoId,jdbcType=INTEGER},",
          "purchase_price = #{purchasePrice,jdbcType=DECIMAL},",
          "tracking_no = #{trackingNo,jdbcType=VARCHAR},",
          "created_by = #{createdBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "call_cnt = #{callCnt,jdbcType=INTEGER},",
          "sts_cd = #{stsCd,jdbcType=CHAR}",
        "where sales_oder_id = #{salesOderId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SaoSalesOrder record);

    List<SendComplexSao> selectSaoStrProByMany(GetASaoQueryByTime getASaoQueryByTime);
}