package neu.train.project.mvo.mapper;

import java.util.List;
import neu.train.project.mvo.domain.mvoProductDescription;
import neu.train.project.mvo.domain.mvoProductDescriptionExample;
import org.apache.ibatis.annotations.Param;

public interface mvoProductDescriptionMapper {
    int countByExample(mvoProductDescriptionExample example);

    int deleteByExample(mvoProductDescriptionExample example);

    int deleteByPrimaryKey(Integer pdnId);

    int insert(mvoProductDescription record);

    int insertSelective(mvoProductDescription record);

    List<mvoProductDescription> selectByExampleWithBLOBs(mvoProductDescriptionExample example);

    List<mvoProductDescription> selectByExample(mvoProductDescriptionExample example);

    mvoProductDescription selectByPrimaryKey(Integer pdnId);

    int updateByExampleSelective(@Param("record") mvoProductDescription record, @Param("example") mvoProductDescriptionExample example);

    int updateByExampleWithBLOBs(@Param("record") mvoProductDescription record, @Param("example") mvoProductDescriptionExample example);

    int updateByExample(@Param("record") mvoProductDescription record, @Param("example") mvoProductDescriptionExample example);

    int updateByPrimaryKeySelective(mvoProductDescription record);

    int updateByPrimaryKeyWithBLOBs(mvoProductDescription record);

    int updateByPrimaryKey(mvoProductDescription record);
}