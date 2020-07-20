package neu.train.project.system.mapper;

import neu.train.project.system.domain.SysDictType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Dict   表 Data 层
 * 
 * @author
 */
@Mapper
public interface SysDictTypeMapper
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
     * 通过Dict   ID Delete Dict   信息
     * 
     * @param dictId Dict   ID
     * @return 结果
     */
    public int deleteDictTypeById(Long dictId);

    /**
     * 批量 Delete Dict   Type 信息
     * 
     * @param dictIds 需要 Delete 的Dict   ID
     * @return 结果
     */
    public int deleteDictTypeByIds(Long[] dictIds);

    /**
     * Add Dict   Type 信息
     * 
     * @param dictType Dict   Type 信息
     * @return 结果
     */
    public int insertDictType(SysDictType dictType);

    /**
     *  Modify  Dict   Type 信息
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
    public SysDictType checkDictTypeUnique(String dictType);
}
