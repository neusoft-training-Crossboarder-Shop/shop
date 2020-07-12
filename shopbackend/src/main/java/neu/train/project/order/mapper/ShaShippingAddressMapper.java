package neu.train.project.order.mapper;

import java.util.List;
import neu.train.project.order.pojo.ShaShippingAddress;
import neu.train.project.order.pojo.ShaShippingAddressExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface ShaShippingAddressMapper {
    int countByExample(ShaShippingAddressExample example);

    int deleteByExample(ShaShippingAddressExample example);

    @Delete({
        "delete from sha_shipping_address",
        "where sha_id = #{shaId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer shaId);

    @Insert({
        "insert into sha_shipping_address (sha_id, sto_id, ",
        "created_by, create_time, ",
        "last_update_by, last_update_time, ",
        "call_cnt, sts_cd, family_name, ",
        "given_name, country_name, ",
        "country_iso_cd, state_or_province_name, ",
        "state_or_province_cd, city_name, ",
        "address_line1, postal_cd, ",
        "contact_phone_no, email, ",
        "freight_cost, carrier_name)",
        "values (#{shaId,jdbcType=INTEGER}, #{stoId,jdbcType=INTEGER}, ",
        "#{createdBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{lastUpdateBy,jdbcType=VARCHAR}, #{lastUpdateTime,jdbcType=TIMESTAMP}, ",
        "#{callCnt,jdbcType=INTEGER}, #{stsCd,jdbcType=CHAR}, #{familyName,jdbcType=VARCHAR}, ",
        "#{givenName,jdbcType=VARCHAR}, #{countryName,jdbcType=VARCHAR}, ",
        "#{countryIsoCd,jdbcType=VARCHAR}, #{stateOrProvinceName,jdbcType=VARCHAR}, ",
        "#{stateOrProvinceCd,jdbcType=VARCHAR}, #{cityName,jdbcType=VARCHAR}, ",
        "#{addressLine1,jdbcType=VARCHAR}, #{postalCd,jdbcType=VARCHAR}, ",
        "#{contactPhoneNo,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
        "#{freightCost,jdbcType=DECIMAL}, #{carrierName,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="shaId", before=false, resultType=Integer.class)
    int insert(ShaShippingAddress record);

    int insertSelective(ShaShippingAddress record);

    List<ShaShippingAddress> selectByExample(ShaShippingAddressExample example);

    @Select({
        "select",
        "sha_id, sto_id, created_by, create_time, last_update_by, last_update_time, call_cnt, ",
        "sts_cd, family_name, given_name, country_name, country_iso_cd, state_or_province_name, ",
        "state_or_province_cd, city_name, address_line1, postal_cd, contact_phone_no, ",
        "email, freight_cost, carrier_name",
        "from sha_shipping_address",
        "where sha_id = #{shaId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    ShaShippingAddress selectByPrimaryKey(Integer shaId);

    int updateByExampleSelective(@Param("record") ShaShippingAddress record, @Param("example") ShaShippingAddressExample example);

    int updateByExample(@Param("record") ShaShippingAddress record, @Param("example") ShaShippingAddressExample example);

    int updateByPrimaryKeySelective(ShaShippingAddress record);

    @Update({
        "update sha_shipping_address",
        "set sto_id = #{stoId,jdbcType=INTEGER},",
          "created_by = #{createdBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "call_cnt = #{callCnt,jdbcType=INTEGER},",
          "sts_cd = #{stsCd,jdbcType=CHAR},",
          "family_name = #{familyName,jdbcType=VARCHAR},",
          "given_name = #{givenName,jdbcType=VARCHAR},",
          "country_name = #{countryName,jdbcType=VARCHAR},",
          "country_iso_cd = #{countryIsoCd,jdbcType=VARCHAR},",
          "state_or_province_name = #{stateOrProvinceName,jdbcType=VARCHAR},",
          "state_or_province_cd = #{stateOrProvinceCd,jdbcType=VARCHAR},",
          "city_name = #{cityName,jdbcType=VARCHAR},",
          "address_line1 = #{addressLine1,jdbcType=VARCHAR},",
          "postal_cd = #{postalCd,jdbcType=VARCHAR},",
          "contact_phone_no = #{contactPhoneNo,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "freight_cost = #{freightCost,jdbcType=DECIMAL},",
          "carrier_name = #{carrierName,jdbcType=VARCHAR}",
        "where sha_id = #{shaId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ShaShippingAddress record);
}