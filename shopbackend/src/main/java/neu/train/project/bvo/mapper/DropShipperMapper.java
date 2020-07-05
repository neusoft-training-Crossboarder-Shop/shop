package neu.train.project.bvo.mapper;

import java.util.List;
import neu.train.project.bvo.domain.DropShipper;
import neu.train.project.bvo.domain.DropShipperExample;
import org.apache.ibatis.annotations.Param;

public interface DropShipperMapper {
    long countByExample(DropShipperExample example);

    int deleteByExample(DropShipperExample example);

    int deleteByPrimaryKey(Integer dsrId);

    int insert(DropShipper record);

    int insertSelective(DropShipper record);

    List<DropShipper> selectByExample(DropShipperExample example);

    DropShipper selectByPrimaryKey(Integer dsrId);

    int updateByExampleSelective(@Param("record") DropShipper record, @Param("example") DropShipperExample example);

    int updateByExample(@Param("record") DropShipper record, @Param("example") DropShipperExample example);

    int updateByPrimaryKeySelective(DropShipper record);

    int updateByPrimaryKey(DropShipper record);
}