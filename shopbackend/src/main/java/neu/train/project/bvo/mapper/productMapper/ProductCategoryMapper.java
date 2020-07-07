package neu.train.project.bvo.mapper.productMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import neu.train.project.bvo.domain.productDomain.ProductCategory;
import neu.train.project.bvo.domain.productDomain.ProductCategoryExample;
import org.apache.ibatis.annotations.Param;

public interface ProductCategoryMapper {

    List<ProductCategory> selectByProId(Integer pro_id);

    List<ProductCategory> selectByParams(Map map);

    long countByExample(ProductCategoryExample example);

    int deleteByExample(ProductCategoryExample example);

    int deleteByPrimaryKey(Integer prcId);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    List<ProductCategory> selectByExample(ProductCategoryExample example);

    ProductCategory selectByPrimaryKey(Integer prcId);

    int updateByExampleSelective(@Param("record") ProductCategory record, @Param("example") ProductCategoryExample example);

    int updateByExample(@Param("record") ProductCategory record, @Param("example") ProductCategoryExample example);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);
}