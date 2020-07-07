package neu.train.project.bvo.mapper.productMapper;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import neu.train.project.bvo.domain.productDomain.Manufacturer;
import neu.train.project.bvo.domain.productDomain.ManufacturerExample;
import org.apache.ibatis.annotations.Param;

public interface ManufacturerMapper {
    long countByExample(ManufacturerExample example);

    int deleteByExample(ManufacturerExample example);

    int deleteByPrimaryKey(Integer manId);

    int insert(Manufacturer record);

    int insertSelective(Manufacturer record);

    List<Manufacturer> selectByExampleWithBLOBs(ManufacturerExample example);

    List<Manufacturer> selectByExample(ManufacturerExample example);

    Manufacturer selectByPrimaryKey(Integer man_id);

    Manufacturer selectByParams(Map map);

    int updateByExampleSelective(@Param("record") Manufacturer record, @Param("example") ManufacturerExample example);

    int updateByExampleWithBLOBs(@Param("record") Manufacturer record, @Param("example") ManufacturerExample example);

    int updateByExample(@Param("record") Manufacturer record, @Param("example") ManufacturerExample example);

    int updateByPrimaryKeySelective(Manufacturer record);

    int updateByPrimaryKeyWithBLOBs(Manufacturer record);

    int updateByPrimaryKey(Manufacturer record);
}