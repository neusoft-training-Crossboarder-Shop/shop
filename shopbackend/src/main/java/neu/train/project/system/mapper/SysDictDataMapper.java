package neu.train.project.system.mapper;

import neu.train.project.system.domain.SysDictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dict   表 Data 层
 * 
 * @author
 */
public interface SysDictDataMapper
{
    /**
     * 根据条件分页查询Dict   Data
     * 
     * @param dictData Dict   Data 信息
     * @return Dict   Data 集合信息
     */
    public List<SysDictData> selectDictDataList(SysDictData dictData);

    /**
     * 根据Dict   Type 查询Dict   Data
     * 
     * @param dictType Dict   Type
     * @return Dict   Data 集合信息
     */
    public List<SysDictData> selectDictDataByType(String dictType);

    /**
     * 根据Dict   Type 和Dict   键值查询Dict   Data 信息
     * 
     * @param dictType Dict   Type
     * @param dictValue Dict   键值
     * @return Dict   标签
     */
    public String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

    /**
     * 根据Dict   Data ID查询信息
     * 
     * @param dictCode Dict   Data ID
     * @return Dict   Data
     */
    public SysDictData selectDictDataById(Long dictCode);

    /**
     * 查询Dict   Data
     * 
     * @param dictType Dict   Type
     * @return Dict   Data
     */
    public int countDictDataByType(String dictType);

    /**
     * 通过Dict   ID Delete Dict   Data 信息
     * 
     * @param dictCode Dict   Data ID
     * @return 结果
     */
    public int deleteDictDataById(Long dictCode);

    /**
     * 批量 Delete Dict   Data 信息
     * 
     * @param dictCodes 需要 Delete 的Dict   Data ID
     * @return 结果
     */
    public int deleteDictDataByIds(Long[] dictCodes);

    /**
     * Add Dict   Data 信息
     * 
     * @param dictData Dict   Data 信息
     * @return 结果
     */
    public int insertDictData(SysDictData dictData);

    /**
     *  Modify  Dict   Data 信息
     * 
     * @param dictData Dict   Data 信息
     * @return 结果
     */
    public int updateDictData(SysDictData dictData);

    /**
     * 同步 Modify  Dict   Type
     * 
     * @param oldDictType 旧Dict   Type
     * @param newDictType 新旧Dict   Type
     * @return 结果
     */
    public int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);
}
