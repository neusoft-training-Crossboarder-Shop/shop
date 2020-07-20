package neu.train.project.system.mapper;

import neu.train.project.system.domain.SysConfig;

import java.util.List;

/**
 * 参数配置 Data 层
 * 
 * @author
 */
public interface SysConfigMapper
{
    /**
     * 查询参数配置信息
     * 
     * @param config 参数配置信息
     * @return 参数配置信息
     */
    public SysConfig selectConfig(SysConfig config);

    /**
     * 查询参数配置列表
     * 
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    public List<SysConfig> selectConfigList(SysConfig config);

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数键名
     * @return 参数配置信息
     */
    public SysConfig checkConfigKeyUnique(String configKey);

    /**
     * Add 参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    public int insertConfig(SysConfig config);

    /**
     *  Modify  参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    public int updateConfig(SysConfig config);

    /**
     *  Delete 参数配置
     * 
     * @param configId 参数ID
     * @return 结果
     */
    public int deleteConfigById(Long configId);

    /**
     * 批量 Delete 参数信息
     * 
     * @param configIds 需要 Delete 的参数ID
     * @return 结果
     */
    public int deleteConfigByIds(Long[] configIds);
}