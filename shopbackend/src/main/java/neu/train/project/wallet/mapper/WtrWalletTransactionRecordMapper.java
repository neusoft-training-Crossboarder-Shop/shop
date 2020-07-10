package neu.train.project.wallet.mapper;

import java.util.List;
import neu.train.project.wallet.pojo.WtrWalletTransactionRecord;
import neu.train.project.wallet.pojo.WtrWalletTransactionRecordExample;
import neu.train.project.wallet.vo.GetATransactionQuery;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
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
        "bank_card_id, transaction_type, ",
        "transaction_money, status, ",
        "balance, business_id, ",
        "finance_type, create_time)",
        "values (#{transactionId,jdbcType=INTEGER}, #{buyerId,jdbcType=INTEGER}, ",
        "#{bankCardId,jdbcType=INTEGER}, #{transactionType,jdbcType=TINYINT}, ",
        "#{transactionMoney,jdbcType=DECIMAL}, #{status,jdbcType=TINYINT}, ",
        "#{balance,jdbcType=DECIMAL}, #{businessId,jdbcType=INTEGER}, ",
        "#{financeType,jdbcType=TINYINT}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="transactionId", before=true, resultType=Integer.class)
    int insert(WtrWalletTransactionRecord record);

    int insertSelective(WtrWalletTransactionRecord record);

    List<WtrWalletTransactionRecord> selectByExample(WtrWalletTransactionRecordExample example);

    @Select({
        "select",
        "transaction_id, buyer_id, bank_card_id, transaction_type, transaction_money, ",
        "status, balance, business_id, finance_type, create_time",
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
          "transaction_type = #{transactionType,jdbcType=TINYINT},",
          "transaction_money = #{transactionMoney,jdbcType=DECIMAL},",
          "status = #{status,jdbcType=TINYINT},",
          "balance = #{balance,jdbcType=DECIMAL},",
          "business_id = #{businessId,jdbcType=INTEGER},",
          "finance_type = #{financeType,jdbcType=TINYINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where transaction_id = #{transactionId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WtrWalletTransactionRecord record);


    List<WtrWalletTransactionRecord> selectByMany(GetATransactionQuery getATransactionQuery);
}