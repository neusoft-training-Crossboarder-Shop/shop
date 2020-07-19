package neu.train.project.mvo.mapper;

import java.util.List;
import neu.train.project.mvo.domain.mvoImage;
import neu.train.project.mvo.domain.mvoImageExample;
import org.apache.ibatis.annotations.Param;

public interface mvoImageMapper {
    int countByExample(mvoImageExample example);

    int deleteByExample(mvoImageExample example);

    int deleteByPrimaryKey(Integer imgId);

    int insert(mvoImage record);

    int insertSelective(mvoImage record);

    List<mvoImage> selectByExample(mvoImageExample example);

    mvoImage selectByPrimaryKey(Integer imgId);

    int updateByExampleSelective(@Param("record") mvoImage record, @Param("example") mvoImageExample example);

    int updateByExample(@Param("record") mvoImage record, @Param("example") mvoImageExample example);

    int updateByPrimaryKeySelective(mvoImage record);

    int updateByPrimaryKey(mvoImage record);
}