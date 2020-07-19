package neu.train.project.mvo.mapper;

import java.util.List;
import neu.train.project.mvo.domain.mvoManufacturer;
import neu.train.project.mvo.domain.mvoManufacturerExample;
import org.apache.ibatis.annotations.Param;

public interface mvoManufacturerMapper {
    int countByExample(mvoManufacturerExample example);

    int deleteByExample(mvoManufacturerExample example);

    int deleteByPrimaryKey(Integer manId);

    int insert(mvoManufacturer record);

    int insertSelective(mvoManufacturer record);

    List<mvoManufacturer> selectByExampleWithBLOBs(mvoManufacturerExample example);

    List<mvoManufacturer> selectByExample(mvoManufacturerExample example);

    mvoManufacturer selectByPrimaryKey(Integer manId);

    int updateByExampleSelective(@Param("record") mvoManufacturer record, @Param("example") mvoManufacturerExample example);

    int updateByExampleWithBLOBs(@Param("record") mvoManufacturer record, @Param("example") mvoManufacturerExample example);

    int updateByExample(@Param("record") mvoManufacturer record, @Param("example") mvoManufacturerExample example);

    int updateByPrimaryKeySelective(mvoManufacturer record);

    int updateByPrimaryKeyWithBLOBs(mvoManufacturer record);

    int updateByPrimaryKey(mvoManufacturer record);
}