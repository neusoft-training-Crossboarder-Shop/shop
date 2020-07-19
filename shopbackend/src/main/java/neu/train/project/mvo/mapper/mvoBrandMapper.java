package neu.train.project.mvo.mapper;

import java.util.List;
import neu.train.project.mvo.domain.mvoBrand;
import neu.train.project.mvo.domain.mvoBrandExample;
import org.apache.ibatis.annotations.Param;

public interface mvoBrandMapper {
    int countByExample(mvoBrandExample example);

    int deleteByExample(mvoBrandExample example);

    int deleteByPrimaryKey(Integer brdId);

    int insert(mvoBrand record);

    int insertSelective(mvoBrand record);

    List<mvoBrand> selectByExample(mvoBrandExample example);

    mvoBrand selectByPrimaryKey(Integer brdId);

    int updateByExampleSelective(@Param("record") mvoBrand record, @Param("example") mvoBrandExample example);

    int updateByExample(@Param("record") mvoBrand record, @Param("example") mvoBrandExample example);

    int updateByPrimaryKeySelective(mvoBrand record);

    int updateByPrimaryKey(mvoBrand record);


    int deleteBrandByIds(int[] Ids);
}