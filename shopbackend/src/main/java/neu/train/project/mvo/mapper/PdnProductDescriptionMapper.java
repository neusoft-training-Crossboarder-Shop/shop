package neu.train.project.mvo.mapper;

import java.util.List;
import neu.train.project.mvo.domain.PdnProductDescription;
import neu.train.project.mvo.domain.PdnProductDescriptionExample;
import org.apache.ibatis.annotations.Param;

public interface PdnProductDescriptionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    long countByExample(PdnProductDescriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    int deleteByExample(PdnProductDescriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer pdnId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    int insert(PdnProductDescription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    int insertSelective(PdnProductDescription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    PdnProductDescription selectOneByExample(PdnProductDescriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    PdnProductDescription selectOneByExampleSelective(@Param("example") PdnProductDescriptionExample example, @Param("selective") PdnProductDescription.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    PdnProductDescription selectOneByExampleWithBLOBs(PdnProductDescriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    List<PdnProductDescription> selectByExampleSelective(@Param("example") PdnProductDescriptionExample example, @Param("selective") PdnProductDescription.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    List<PdnProductDescription> selectByExampleWithBLOBs(PdnProductDescriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    List<PdnProductDescription> selectByExample(PdnProductDescriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    PdnProductDescription selectByPrimaryKeySelective(@Param("pdnId") Integer pdnId, @Param("selective") PdnProductDescription.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    PdnProductDescription selectByPrimaryKey(Integer pdnId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    PdnProductDescription selectByPrimaryKeyWithLogicalDelete(@Param("pdnId") Integer pdnId, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") PdnProductDescription record, @Param("example") PdnProductDescriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") PdnProductDescription record, @Param("example") PdnProductDescriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") PdnProductDescription record, @Param("example") PdnProductDescriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PdnProductDescription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(PdnProductDescription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PdnProductDescription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") PdnProductDescriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer pdnId);
}