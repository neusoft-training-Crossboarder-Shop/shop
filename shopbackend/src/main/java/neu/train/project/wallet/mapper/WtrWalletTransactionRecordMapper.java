package neu.train.project.wallet.mapper;

import java.util.List;
import neu.train.project.wallet.pojo.WtrWalletTransactionRecord;
import neu.train.project.wallet.pojo.WtrWalletTransactionRecordExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface WtrWalletTransactionRecordMapper {
    int countByExample(WtrWalletTransactionRecordExample example);

    int deleteByExample(WtrWalletTransactionRecordExample example);

    @Delete({
        "delete from wtr_wallet_transaction_record",
        "where transaction_id = #{transactionId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer transactionId);

    @Insert({
        "insert into wtr_wallet_transaction_record (transaction_id, buyer_id, ",
        "bank_card_id, transaction_number, ",
        "transaction_type, account_name, ",
        "transaction_money, commission, ",
        "discount_commission, transaction_method, ",
        "complete_time, status, ",
        "actual_money, actual_commission, ",
        "actual_discount_commission, balance, ",
        "business_id, finance_type, ",
        "note, operator_name, ",
        "operator_ip, created_By, ",
        "create_time, last_update_by, ",
        "last_update_time, bank_receipt_url, ",
        "exchange_rate, transaction_desc, ",
        "foreign_exchange_fee, withdraw_fee)",
        "values (#{transactionId,jdbcType=INTEGER}, #{buyerId,jdbcType=INTEGER}, ",
        "#{bankCardId,jdbcType=INTEGER}, #{transactionNumber,jdbcType=VARCHAR}, ",
        "#{transactionType,jdbcType=TINYINT}, #{accountName,jdbcType=VARCHAR}, ",
        "#{transactionMoney,jdbcType=DECIMAL}, #{commission,jdbcType=DECIMAL}, ",
        "#{discountCommission,jdbcType=DECIMAL}, #{transactionMethod,jdbcType=TINYINT}, ",
        "#{completeTime,jdbcType=TIMESTAMP}, #{status,jdbcType=TINYINT}, ",
        "#{actualMoney,jdbcType=DECIMAL}, #{actualCommission,jdbcType=DECIMAL}, ",
        "#{actualDiscountCommission,jdbcType=DECIMAL}, #{balance,jdbcType=DECIMAL}, ",
        "#{businessId,jdbcType=INTEGER}, #{financeType,jdbcType=TINYINT}, ",
        "#{note,jdbcType=VARCHAR}, #{operatorName,jdbcType=VARCHAR}, ",
        "#{operatorIp,jdbcType=VARCHAR}, #{createdBy,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastUpdateBy,jdbcType=VARCHAR}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP}, #{bankReceiptUrl,jdbcType=VARCHAR}, ",
        "#{exchangeRate,jdbcType=DECIMAL}, #{transactionDesc,jdbcType=VARCHAR}, ",
        "#{foreignExchangeFee,jdbcType=DECIMAL}, #{withdrawFee,jdbcType=DECIMAL})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="transactionId", before=true, resultType=Integer.class)
    int insert(WtrWalletTransactionRecord record);

    int insertSelective(WtrWalletTransactionRecord record);

    List<WtrWalletTransactionRecord> selectByExample(WtrWalletTransactionRecordExample example);

    @Select({
        "select",
        "transaction_id, buyer_id, bank_card_id, transaction_number, transaction_type, ",
        "account_name, transaction_money, commission, discount_commission, transaction_method, ",
        "complete_time, status, actual_money, actual_commission, actual_discount_commission, ",
        "balance, business_id, finance_type, note, operator_name, operator_ip, created_By, ",
        "create_time, last_update_by, last_update_time, bank_receipt_url, exchange_rate, ",
        "transaction_desc, foreign_exchange_fee, withdraw_fee",
        "from wtr_wallet_transaction_record",
        "where transaction_id = #{transactionId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    WtrWalletTransactionRecord selectByPrimaryKey(Integer transactionId);

    int updateByExampleSelective(@Param("record") WtrWalletTransactionRecord record, @Param("example") WtrWalletTransactionRecordExample example);

    int updateByExample(@Param("record") WtrWalletTransactionRecord record, @Param("example") WtrWalletTransactionRecordExample example);

    int updateByPrimaryKeySelective(WtrWalletTransactionRecord record);

    @Update({
        "update wtr_wallet_transaction_record",
        "set buyer_id = #{buyerId,jdbcType=INTEGER},",
          "bank_card_id = #{bankCardId,jdbcType=INTEGER},",
          "transaction_number = #{transactionNumber,jdbcType=VARCHAR},",
          "transaction_type = #{transactionType,jdbcType=TINYINT},",
          "account_name = #{accountName,jdbcType=VARCHAR},",
          "transaction_money = #{transactionMoney,jdbcType=DECIMAL},",
          "commission = #{commission,jdbcType=DECIMAL},",
          "discount_commission = #{discountCommission,jdbcType=DECIMAL},",
          "transaction_method = #{transactionMethod,jdbcType=TINYINT},",
          "complete_time = #{completeTime,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=TINYINT},",
          "actual_money = #{actualMoney,jdbcType=DECIMAL},",
          "actual_commission = #{actualCommission,jdbcType=DECIMAL},",
          "actual_discount_commission = #{actualDiscountCommission,jdbcType=DECIMAL},",
          "balance = #{balance,jdbcType=DECIMAL},",
          "business_id = #{businessId,jdbcType=INTEGER},",
          "finance_type = #{financeType,jdbcType=TINYINT},",
          "note = #{note,jdbcType=VARCHAR},",
          "operator_name = #{operatorName,jdbcType=VARCHAR},",
          "operator_ip = #{operatorIp,jdbcType=VARCHAR},",
          "created_By = #{createdBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "bank_receipt_url = #{bankReceiptUrl,jdbcType=VARCHAR},",
          "exchange_rate = #{exchangeRate,jdbcType=DECIMAL},",
          "transaction_desc = #{transactionDesc,jdbcType=VARCHAR},",
          "foreign_exchange_fee = #{foreignExchangeFee,jdbcType=DECIMAL},",
          "withdraw_fee = #{withdrawFee,jdbcType=DECIMAL}",
        "where transaction_id = #{transactionId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WtrWalletTransactionRecord record);
}