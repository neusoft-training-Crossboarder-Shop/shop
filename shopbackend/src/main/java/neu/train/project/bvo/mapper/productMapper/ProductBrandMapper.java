package neu.train.project.bvo.mapper.productMapper;

import java.util.List;
import neu.train.project.bvo.domain.productDomain.ProductBrand;
import neu.train.project.bvo.domain.productDomain.ProductBrandExample;
import org.apache.ibatis.annotations.Param;

public interface ProductBrandMapper {
    long countByExample(ProductBrandExample example);

    int deleteByExample(ProductBrandExample example);

    int deleteByPrimaryKey(Integer brdId);

    int insert(ProductBrand record);

    int insertSelective(ProductBrand record);

    List<ProductBrand> selectByExample(ProductBrandExample example);

    ProductBrand selectByPrimaryKey(Integer brd_id);

    int updateByExampleSelective(@Param("record") ProductBrand record, @Param("example") ProductBrandExample example);

    int updateByExample(@Param("record") ProductBrand record, @Param("example") ProductBrandExample example);

    int updateByPrimaryKeySelective(ProductBrand record);

    int updateByPrimaryKey(ProductBrand record);
}