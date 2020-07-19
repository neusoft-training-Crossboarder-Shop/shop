package neu.train.project.mvo.mapper;

import java.util.List;
import neu.train.project.mvo.domain.mvoProduct;
import neu.train.project.mvo.domain.mvoProductExample;
import neu.train.project.mvo.domain.vo.MvoSearchProduct;
import org.apache.ibatis.annotations.Param;

public interface mvoProductMapper {

    List<mvoProduct> selectBySearchProduct(MvoSearchProduct product);

    mvoProduct selectByProId(Integer proId);


    int countByExample(mvoProductExample example);

    int deleteByExample(mvoProductExample example);

    int deleteByPrimaryKey(Integer proId);

    int insert(mvoProduct record);

    int insertSelective(mvoProduct record);

    List<mvoProduct> selectByExampleWithBLOBs(mvoProductExample example);

    List<mvoProduct> selectByExample(mvoProductExample example);

    mvoProduct selectByPrimaryKey(Integer proId);

    int updateByExampleSelective(@Param("record") mvoProduct record, @Param("example") mvoProductExample example);

    int updateByExampleWithBLOBs(@Param("record") mvoProduct record, @Param("example") mvoProductExample example);

    int updateByExample(@Param("record") mvoProduct record, @Param("example") mvoProductExample example);

    int updateByPrimaryKeySelective(mvoProduct record);

    int updateByPrimaryKeyWithBLOBs(mvoProduct record);

    int updateByPrimaryKey(mvoProduct record);


}