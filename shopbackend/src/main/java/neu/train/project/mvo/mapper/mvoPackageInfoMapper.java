package neu.train.project.mvo.mapper;

import java.util.List;
import neu.train.project.mvo.domain.mvoPackageInfo;
import neu.train.project.mvo.domain.mvoPackageInfoExample;
import org.apache.ibatis.annotations.Param;

public interface mvoPackageInfoMapper {
    int countByExample(mvoPackageInfoExample example);

    int deleteByExample(mvoPackageInfoExample example);

    int deleteByPrimaryKey(Integer pckId);

    int insert(mvoPackageInfo record);

    int insertSelective(mvoPackageInfo record);

    List<mvoPackageInfo> selectByExample(mvoPackageInfoExample example);

    mvoPackageInfo selectByPrimaryKey(Integer pckId);

    int updateByExampleSelective(@Param("record") mvoPackageInfo record, @Param("example") mvoPackageInfoExample example);

    int updateByExample(@Param("record") mvoPackageInfo record, @Param("example") mvoPackageInfoExample example);

    int updateByPrimaryKeySelective(mvoPackageInfo record);

    int updateByPrimaryKey(mvoPackageInfo record);
}