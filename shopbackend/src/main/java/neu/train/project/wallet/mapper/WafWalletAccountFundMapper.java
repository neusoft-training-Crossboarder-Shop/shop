package neu.train.project.wallet.mapper;

import java.util.List;
import neu.train.project.wallet.pojo.WafWalletAccountFund;
import neu.train.project.wallet.pojo.WafWalletAccountFundExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface WafWalletAccountFundMapper {
    int countByExample(WafWalletAccountFundExample example);

    int deleteByExample(WafWalletAccountFundExample example);

    @Delete({
        "delete from waf_wallet_account_fund",
        "where buyer_id = #{buyerId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer buyerId);

    @Insert({
        "insert into waf_wallet_account_fund (buyer_id, available_money, ",
        "depositing_money, withdrawing_money, ",
        "created_By, create_time, ",
        "last_update_by, last_update_time, ",
        "currency, version)",
        "values (#{buyerId,jdbcType=INTEGER}, #{availableMoney,jdbcType=DECIMAL}, ",
        "#{depositingMoney,jdbcType=DECIMAL}, #{withdrawingMoney,jdbcType=DECIMAL}, ",
        "#{createdBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{lastUpdateBy,jdbcType=VARCHAR}, #{lastUpdateTime,jdbcType=TIMESTAMP}, ",
        "#{currency,jdbcType=VARCHAR}, #{version,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="buyerId", before=true, resultType=Integer.class)
    int insert(WafWalletAccountFund record);

    int insertSelective(WafWalletAccountFund record);

    List<WafWalletAccountFund> selectByExample(WafWalletAccountFundExample example);

    @Select({
        "select",
        "buyer_id, available_money, depositing_money, withdrawing_money, created_By, ",
        "create_time, last_update_by, last_update_time, currency, version",
        "from waf_wallet_account_fund",
        "where buyer_id = #{buyerId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    WafWalletAccountFund selectByPrimaryKey(Integer buyerId);

    int updateByExampleSelective(@Param("record") WafWalletAccountFund record, @Param("example") WafWalletAccountFundExample example);

    int updateByExample(@Param("record") WafWalletAccountFund record, @Param("example") WafWalletAccountFundExample example);

    int updateByPrimaryKeySelective(WafWalletAccountFund record);

    @Update({
        "update waf_wallet_account_fund",
        "set available_money = #{availableMoney,jdbcType=DECIMAL},",
          "depositing_money = #{depositingMoney,jdbcType=DECIMAL},",
          "withdrawing_money = #{withdrawingMoney,jdbcType=DECIMAL},",
          "created_By = #{createdBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "currency = #{currency,jdbcType=VARCHAR},",
          "version = #{version,jdbcType=INTEGER}",
        "where buyer_id = #{buyerId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WafWalletAccountFund record);
}