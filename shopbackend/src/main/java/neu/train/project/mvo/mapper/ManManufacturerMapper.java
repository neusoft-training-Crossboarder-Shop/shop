package neu.train.project.mvo.mapper;

import java.util.List;
import neu.train.project.mvo.domain.ManManufacturer;
import neu.train.project.mvo.domain.ManManufacturerExample;
import org.apache.ibatis.annotations.Param;

public interface ManManufacturerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    long countByExample(ManManufacturerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    int deleteByExample(ManManufacturerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer manId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    int insert(ManManufacturer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    int insertSelective(ManManufacturer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    ManManufacturer selectOneByExample(ManManufacturerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    ManManufacturer selectOneByExampleSelective(@Param("example") ManManufacturerExample example, @Param("selective") ManManufacturer.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    ManManufacturer selectOneByExampleWithBLOBs(ManManufacturerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    List<ManManufacturer> selectByExampleSelective(@Param("example") ManManufacturerExample example, @Param("selective") ManManufacturer.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    List<ManManufacturer> selectByExampleWithBLOBs(ManManufacturerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    List<ManManufacturer> selectByExample(ManManufacturerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    ManManufacturer selectByPrimaryKeySelective(@Param("manId") Integer manId, @Param("selective") ManManufacturer.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    ManManufacturer selectByPrimaryKey(Integer manId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    ManManufacturer selectByPrimaryKeyWithLogicalDelete(@Param("manId") Integer manId, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ManManufacturer record, @Param("example") ManManufacturerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") ManManufacturer record, @Param("example") ManManufacturerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ManManufacturer record, @Param("example") ManManufacturerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ManManufacturer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(ManManufacturer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ManManufacturer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") ManManufacturerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_manufacturer
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer manId);
}