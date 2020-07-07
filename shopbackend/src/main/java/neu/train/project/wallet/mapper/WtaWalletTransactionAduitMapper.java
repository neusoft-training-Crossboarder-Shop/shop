package neu.train.project.wallet.mapper;

import java.util.List;
import neu.train.project.wallet.pojo.WtaWalletTransactionAduit;
import neu.train.project.wallet.pojo.WtaWalletTransactionAduitExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface WtaWalletTransactionAduitMapper {
    int countByExample(WtaWalletTransactionAduitExample example);

    int deleteByExample(WtaWalletTransactionAduitExample example);

    @Delete({
        "delete from wta_wallet_transaction_aduit",
        "where transaction_audit_id = #{transactionAuditId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer transactionAuditId);

    @Insert({
        "insert into wta_wallet_transaction_aduit (transaction_audit_id, buyer_id, ",
        "transaction_id, available_money_before, ",
        "depositing_money_before, withdrawing_money_before, ",
        "operate_money, operate_type, ",
        "available_money_after, depositing_money_after, ",
        "withdrawing_money_after, operate_by, ",
        "status, created_By, ",
        "create_time, last_update_by, ",
        "last_update_time)",
        "values (#{transactionAuditId,jdbcType=INTEGER}, #{buyerId,jdbcType=INTEGER}, ",
        "#{transactionId,jdbcType=INTEGER}, #{availableMoneyBefore,jdbcType=DECIMAL}, ",
        "#{depositingMoneyBefore,jdbcType=DECIMAL}, #{withdrawingMoneyBefore,jdbcType=DECIMAL}, ",
        "#{operateMoney,jdbcType=DECIMAL}, #{operateType,jdbcType=TINYINT}, ",
        "#{availableMoneyAfter,jdbcType=DECIMAL}, #{depositingMoneyAfter,jdbcType=DECIMAL}, ",
        "#{withdrawingMoneyAfter,jdbcType=DECIMAL}, #{operateBy,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=TINYINT}, #{createdBy,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastUpdateBy,jdbcType=VARCHAR}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="transactionAuditId", before=true, resultType=Integer.class)
    int insert(WtaWalletTransactionAduit record);

    int insertSelective(WtaWalletTransactionAduit record);

    List<WtaWalletTransactionAduit> selectByExample(WtaWalletTransactionAduitExample example);

    @Select({
        "select",
        "transaction_audit_id, buyer_id, transaction_id, available_money_before, depositing_money_before, ",
        "withdrawing_money_before, operate_money, operate_type, available_money_after, ",
        "depositing_money_after, withdrawing_money_after, operate_by, status, created_By, ",
        "create_time, last_update_by, last_update_time",
        "from wta_wallet_transaction_aduit",
        "where transaction_audit_id = #{transactionAuditId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    WtaWalletTransactionAduit selectByPrimaryKey(Integer transactionAuditId);

    int updateByExampleSelective(@Param("record") WtaWalletTransactionAduit record, @Param("example") WtaWalletTransactionAduitExample example);

    int updateByExample(@Param("record") WtaWalletTransactionAduit record, @Param("example") WtaWalletTransactionAduitExample example);

    int updateByPrimaryKeySelective(WtaWalletTransactionAduit record);

    @Update({
        "update wta_wallet_transaction_aduit",
        "set buyer_id = #{buyerId,jdbcType=INTEGER},",
          "transaction_id = #{transactionId,jdbcType=INTEGER},",
          "available_money_before = #{availableMoneyBefore,jdbcType=DECIMAL},",
          "depositing_money_before = #{depositingMoneyBefore,jdbcType=DECIMAL},",
          "withdrawing_money_before = #{withdrawingMoneyBefore,jdbcType=DECIMAL},",
          "operate_money = #{operateMoney,jdbcType=DECIMAL},",
          "operate_type = #{operateType,jdbcType=TINYINT},",
          "available_money_after = #{availableMoneyAfter,jdbcType=DECIMAL},",
          "depositing_money_after = #{depositingMoneyAfter,jdbcType=DECIMAL},",
          "withdrawing_money_after = #{withdrawingMoneyAfter,jdbcType=DECIMAL},",
          "operate_by = #{operateBy,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=TINYINT},",
          "created_By = #{createdBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP}",
        "where transaction_audit_id = #{transactionAuditId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WtaWalletTransactionAduit record);
}