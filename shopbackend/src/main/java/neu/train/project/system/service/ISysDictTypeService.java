package neu.train.project.system.service;

import java.util.List;

import neu.train.project.system.domain.SysDictData;
import neu.train.project.system.domain.SysDictType;

/**
 * Dict    业务层
 * 
 * @author
 */
public interface ISysDictTypeService
{
    /**
     * 根据条件分页查询Dict   Type
     * 
     * @param dictType Dict   Type 信息
     * @return Dict   Type 集合信息
     */
    public List<SysDictType> selectDictTypeList(SysDictType dictType);

    /**
     * 根据所有Dict   Type
     * 
     * @return Dict   Type 集合信息
     */
    public List<SysDictType> selectDictTypeAll();

    /**
     * 根据Dict   Type 查询Dict   Data
     * 
     * @param dictType Dict   Type
     * @return Dict   Data 集合信息
     */
    public List<SysDictData> selectDictDataByType(String dictType);

    /**
     * 根据Dict   Type ID查询信息
     * 
     * @param dictId Dict   Type ID
     * @return Dict   Type
     */
    public SysDictType selectDictTypeById(Long dictId);

    /**
     * 根据Dict   Type 查询信息
     * 
     * @param dictType Dict   Type
     * @return Dict   Type
     */
    public SysDictType selectDictTypeByType(String dictType);

    /**
     * 批量 Delete Dict   信息
     * 
     * @param dictIds 需要 Delete 的Dict   ID
     * @return 结果
     */
    public int deleteDictTypeByIds(Long[] dictIds);

    /**
     * 清空缓存Data
     */
    public void clearCache();

    /**
     * Add 保存Dict   Type 信息
     * 
     * @param dictType Dict   Type 信息
     * @return 结果
     */
    public int insertDictType(SysDictType dictType);

    /**
     *  Modify  保存Dict   Type 信息
     * 
     * @param dictType Dict   Type 信息
     * @return 结果
     */
    public int updateDictType(SysDictType dictType);

    /**
     * 校验Dict   Type 称是否唯一
     * 
     * @param dictType Dict   Type
     * @return 结果
     */
    public String checkDictTypeUnique(SysDictType dictType);
}
