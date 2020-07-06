package neu.train.project.bvo.mapper.productMapper;

import java.util.List;
import neu.train.project.bvo.domain.productDomain.ProductPackageInfo;
import neu.train.project.bvo.domain.productDomain.ProductPackageInfoExample;
import org.apache.ibatis.annotations.Param;

public interface ProductPackageInfoMapper {


    List<ProductPackageInfo> selectByProId(Integer pro_id);

    long countByExample(ProductPackageInfoExample example);

    int deleteByExample(ProductPackageInfoExample example);

    int deleteByPrimaryKey(Integer pckId);

    int insert(ProductPackageInfo record);

    int insertSelective(ProductPackageInfo record);
    List<ProductPackageInfo> selectByExample(ProductPackageInfoExample example);

    ProductPackageInfo selectByPrimaryKey(Integer pckId);

    int updateByExampleSelective(@Param("record") ProductPackageInfo record, @Param("example") ProductPackageInfoExample example);

    int updateByExample(@Param("record") ProductPackageInfo record, @Param("example") ProductPackageInfoExample example);

    int updateByPrimaryKeySelective(ProductPackageInfo record);

    int updateByPrimaryKey(ProductPackageInfo record);
}