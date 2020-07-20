package neu.train.project.system.service.impl;

import neu.train.common.utils.DictUtils;
import neu.train.project.system.domain.SysDictData;
import neu.train.project.system.mapper.SysDictDataMapper;
import neu.train.project.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Dict    业务层处理
 * 
 * @author
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService
{
    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询Dict   Data
     * 
     * @param dictData Dict   Data 信息
     * @return Dict   Data 集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData)
    {
        return dictDataMapper.selectDictDataList(dictData);
    }

    /**
     * 根据Dict   Type 和Dict   键值查询Dict   Data 信息
     * 
     * @param dictType Dict   Type
     * @param dictValue Dict   键值
     * @return Dict   标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue)
    {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据Dict   Data ID查询信息
     * 
     * @param dictCode Dict   Data ID
     * @return Dict   Data
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode)
    {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    /**
     * 批量 Delete Dict   Data 信息
     * 
     * @param dictCodes 需要 Delete 的Dict   Data ID
     * @return 结果
     */
    @Override
    public int deleteDictDataByIds(Long[] dictCodes)
    {
        int row = dictDataMapper.deleteDictDataByIds(dictCodes);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * Add 保存Dict   Data 信息
     * 
     * @param dictData Dict   Data 信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictData dictData)
    {
        int row = dictDataMapper.insertDictData(dictData);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     *  Modify  保存Dict   Data 信息
     * 
     * @param dictData Dict   Data 信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictData dictData)
    {
        int row = dictDataMapper.updateDictData(dictData);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }
}
