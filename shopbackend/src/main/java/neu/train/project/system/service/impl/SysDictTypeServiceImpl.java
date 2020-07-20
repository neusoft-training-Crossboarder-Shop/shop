package neu.train.project.system.service.impl;

import java.util.List;
import javax.annotation.PostConstruct;

import neu.train.common.constant.UserConstants;
import neu.train.common.exception.CustomException;
import neu.train.common.utils.DictUtils;
import neu.train.common.utils.StringUtils;
import neu.train.project.system.domain.SysDictData;
import neu.train.project.system.domain.SysDictType;
import neu.train.project.system.mapper.SysDictDataMapper;
import neu.train.project.system.mapper.SysDictTypeMapper;
import neu.train.project.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dict    业务层处理
 * 
 * @author
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService{
    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 项目启动时，初始化Dict   到缓存
     */
    @PostConstruct
    public void init()
    {
        List<SysDictType> dictTypeList = dictTypeMapper.selectDictTypeAll();
        for (SysDictType dictType : dictTypeList)
        {
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(dictType.getDictType());
            DictUtils.setDictCache(dictType.getDictType(), dictDatas);
        }
    }

    /**
     * 根据条件分页查询Dict   Type
     * 
     * @param dictType Dict   Type 信息
     * @return Dict   Type 集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType)
    {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    /**
     * 根据所有Dict   Type
     * 
     * @return Dict   Type 集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll()
    {
        return dictTypeMapper.selectDictTypeAll();
    }

    /**
     * 根据Dict   Type 查询Dict   Data
     * 
     * @param dictType Dict   Type
     * @return Dict   Data 集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType)
    {
        List<SysDictData> dictDatas = DictUtils.getDictCache(dictType);
        if (StringUtils.isNotNull(dictDatas))
        {
            return dictDatas;
        }
        dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotNull(dictDatas))
        {
            DictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return null;
    }

    /**
     * 根据Dict   Type ID查询信息
     * 
     * @param dictId Dict   Type ID
     * @return Dict   Type
     */
    @Override
    public SysDictType selectDictTypeById(Long dictId)
    {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    /**
     * 根据Dict   Type 查询信息
     * 
     * @param dictType Dict   Type
     * @return Dict   Type
     */
    @Override
    public SysDictType selectDictTypeByType(String dictType)
    {
        return dictTypeMapper.selectDictTypeByType(dictType);
    }

    /**
     * 批量 Delete Dict   Type 信息
     * 
     * @param dictIds 需要 Delete 的Dict   ID
     * @return 结果
     */
    @Override
    public int deleteDictTypeByIds(Long[] dictIds)
    {
        for (Long dictId : dictIds)
        {
            SysDictType dictType = selectDictTypeById(dictId);
            if (dictDataMapper.countDictDataByType(dictType.getDictType()) > 0)
            {
                throw new CustomException(String.format("%1$sAlready分配,不能 Delete ", dictType.getDictName()));
            }
        }
        int count = dictTypeMapper.deleteDictTypeByIds(dictIds);
        if (count > 0)
        {
            DictUtils.clearDictCache();
        }
        return count;
    }

    /**
     * 清空缓存Data
     */
    @Override
    public void clearCache()
    {
        DictUtils.clearDictCache();
    }

    /**
     * Add 保存Dict   Type 信息
     * 
     * @param dictType Dict   Type 信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictType dictType)
    {
        int row = dictTypeMapper.insertDictType(dictType);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     *  Modify  保存Dict   Type 信息
     * 
     * @param dictType Dict   Type 信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDictType(SysDictType dictType)
    {
        SysDictType oldDict = dictTypeMapper.selectDictTypeById(dictType.getDictId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        int row = dictTypeMapper.updateDictType(dictType);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 校验Dict   Type 称是否唯一
     * 
     * @param dict Dict   Type
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictType dict)
    {
        Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        SysDictType dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
