package neu.train.project.system.service;

import java.util.List;
import neu.train.project.system.domain.SysDictData;

/**
 * Dict    业务层
 * 
 * @author
 */
public interface ISysDictDataService
{
    /**
     * 根据条件分页查询Dict   Data
     * 
     * @param dictData Dict   Data 信息
     * @return Dict   Data 集合信息
     */
    public List<SysDictData> selectDictDataList(SysDictData dictData);

    /**
     * 根据Dict   Type 和Dict   键值查询Dict   Data 信息
     * 
     * @param dictType Dict   Type
     * @param dictValue Dict   键值
     * @return Dict   标签
     */
    public String selectDictLabel(String dictType, String dictValue);

    /**
     * 根据Dict   Data ID查询信息
     * 
     * @param dictCode Dict   Data ID
     * @return Dict   Data
     */
    public SysDictData selectDictDataById(Long dictCode);

    /**
     * 批量 Delete Dict   Data 信息
     * 
     * @param dictCodes 需要 Delete 的Dict   Data ID
     * @return 结果
     */
    public int deleteDictDataByIds(Long[] dictCodes);

    /**
     * Add 保存Dict   Data 信息
     * 
     * @param dictData Dict   Data 信息
     * @return 结果
     */
    public int insertDictData(SysDictData dictData);

    /**
     *  Modify  保存Dict   Data 信息
     * 
     * @param dictData Dict   Data 信息
     * @return 结果
     */
    public int updateDictData(SysDictData dictData);
}
