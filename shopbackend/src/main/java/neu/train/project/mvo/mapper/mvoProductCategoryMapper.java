package neu.train.project.mvo.mapper;

import java.util.List;
import neu.train.project.mvo.domain.mvoProductCategory;
import neu.train.project.mvo.domain.mvoProductCategoryExample;
import org.apache.ibatis.annotations.Param;

public interface mvoProductCategoryMapper {
    int countByExample(mvoProductCategoryExample example);

    int deleteByExample(mvoProductCategoryExample example);

    int deleteByPrimaryKey(Integer prcId);

    int insert(mvoProductCategory record);

    int insertSelective(mvoProductCategory record);

    List<mvoProductCategory> selectByExample(mvoProductCategoryExample example);

    mvoProductCategory selectByPrimaryKey(Integer prcId);

    int updateByExampleSelective(@Param("record") mvoProductCategory record, @Param("example") mvoProductCategoryExample example);

    int updateByExample(@Param("record") mvoProductCategory record, @Param("example") mvoProductCategoryExample example);

    int updateByPrimaryKeySelective(mvoProductCategory record);

    int updateByPrimaryKey(mvoProductCategory record);
}