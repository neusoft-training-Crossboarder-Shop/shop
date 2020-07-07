package neu.train.project.wallet.mapper;

import java.util.List;
import neu.train.project.wallet.pojo.WaaWalletAccount;
import neu.train.project.wallet.pojo.WaaWalletAccountExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface WaaWalletAccountMapper {
    int countByExample(WaaWalletAccountExample example);

    int deleteByExample(WaaWalletAccountExample example);

    @Delete({
        "delete from waa_wallet_account",
        "where buyer_id = #{buyerId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer buyerId);

    @Insert({
        "insert into waa_wallet_account (buyer_id, account_name, ",
        "email, password, ",
        "active_time, is_active, ",
        "status, created_By, ",
        "create_time, last_update_by, ",
        "last_update_time, account_type, ",
        "hold_order_time, auto_pay_status)",
        "values (#{buyerId,jdbcType=INTEGER}, #{accountName,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{activeTime,jdbcType=TIMESTAMP}, #{isActive,jdbcType=CHAR}, ",
        "#{status,jdbcType=TINYINT}, #{createdBy,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastUpdateBy,jdbcType=VARCHAR}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP}, #{accountType,jdbcType=INTEGER}, ",
        "#{holdOrderTime,jdbcType=CHAR}, #{autoPayStatus,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="buyerId", before=false, resultType=Integer.class)
    int insert(WaaWalletAccount record);

    int insertSelective(WaaWalletAccount record);

    List<WaaWalletAccount> selectByExample(WaaWalletAccountExample example);

    @Select({
        "select",
        "buyer_id, account_name, email, password, active_time, is_active, status, created_By, ",
        "create_time, last_update_by, last_update_time, account_type, hold_order_time, ",
        "auto_pay_status",
        "from waa_wallet_account",
        "where buyer_id = #{buyerId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    WaaWalletAccount selectByPrimaryKey(Integer buyerId);

    int updateByExampleSelective(@Param("record") WaaWalletAccount record, @Param("example") WaaWalletAccountExample example);

    int updateByExample(@Param("record") WaaWalletAccount record, @Param("example") WaaWalletAccountExample example);

    int updateByPrimaryKeySelective(WaaWalletAccount record);

    @Update({
        "update waa_wallet_account",
        "set account_name = #{accountName,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "active_time = #{activeTime,jdbcType=TIMESTAMP},",
          "is_active = #{isActive,jdbcType=CHAR},",
          "status = #{status,jdbcType=TINYINT},",
          "created_By = #{createdBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "account_type = #{accountType,jdbcType=INTEGER},",
          "hold_order_time = #{holdOrderTime,jdbcType=CHAR},",
          "auto_pay_status = #{autoPayStatus,jdbcType=VARCHAR}",
        "where buyer_id = #{buyerId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WaaWalletAccount record);
}