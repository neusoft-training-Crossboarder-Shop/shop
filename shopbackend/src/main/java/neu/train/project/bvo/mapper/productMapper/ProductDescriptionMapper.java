package neu.train.project.bvo.mapper.productMapper;

import java.util.List;
import neu.train.project.bvo.domain.productDomain.ProductDescription;
import neu.train.project.bvo.domain.productDomain.ProductDescriptionExample;
import org.apache.ibatis.annotations.Param;

public interface ProductDescriptionMapper {


    List<ProductDescription> selectByProId(Integer pro_id);


    long countByExample(ProductDescriptionExample example);

    int deleteByExample(ProductDescriptionExample example);

    int deleteByPrimaryKey(Integer pdnId);

    int insert(ProductDescription record);

    int insertSelective(ProductDescription record);
    List<ProductDescription> selectByExampleWithBLOBs(ProductDescriptionExample example);

    List<ProductDescription> selectByExample(ProductDescriptionExample example);

    ProductDescription selectByPrimaryKey(Integer pdnId);

    int updateByExampleSelective(@Param("record") ProductDescription record, @Param("example") ProductDescriptionExample example);

    int updateByExampleWithBLOBs(@Param("record") ProductDescription record, @Param("example") ProductDescriptionExample example);

    int updateByExample(@Param("record") ProductDescription record, @Param("example") ProductDescriptionExample example);

    int updateByPrimaryKeySelective(ProductDescription record);

    int updateByPrimaryKeyWithBLOBs(ProductDescription record);

    int updateByPrimaryKey(ProductDescription record);
}