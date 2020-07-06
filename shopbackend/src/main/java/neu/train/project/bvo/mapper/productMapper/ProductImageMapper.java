package neu.train.project.bvo.mapper.productMapper;

import java.util.List;
import neu.train.project.bvo.domain.productDomain.ProductImage;
import neu.train.project.bvo.domain.productDomain.ProductImageExample;
import org.apache.ibatis.annotations.Param;

public interface ProductImageMapper {

    List<ProductImage> selectByProId(Integer pro_id);

    long countByExample(ProductImageExample example);

    int deleteByExample(ProductImageExample example);

    int deleteByPrimaryKey(Integer imgId);

    int insert(ProductImage record);

    int insertSelective(ProductImage record);

    List<ProductImage> selectByExample(ProductImageExample example);

    ProductImage selectByPrimaryKey(Integer imgId);

    int updateByExampleSelective(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    int updateByExample(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    int updateByPrimaryKeySelective(ProductImage record);

    int updateByPrimaryKey(ProductImage record);
}