package neu.train.project.system.service;

import neu.train.project.system.domain.SysConfig;

import java.util.List;

/**
 * 参数配置 服务层
 * 
 * @author
 */
public interface ISysConfigService
{
    /**
     * 查询参数配置信息
     * 
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    public SysConfig selectConfigById(Long configId);

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数键名
     * @return 参数键值
     */
    public String selectConfigByKey(String configKey);

    /**
     * 查询参数配置列表
     * 
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    public List<SysConfig> selectConfigList(SysConfig config);

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
     * 批量 Delete 参数信息
     * 
     * @param configIds 需要 Delete 的参数ID
     * @return 结果
     */
    public int deleteConfigByIds(Long[] configIds);

    /**
     * 清空缓存Data
     */
    public void clearCache();

    /**
     * 校验参数键名是否唯一
     * 
     * @param config 参数信息
     * @return 结果
     */
    public String checkConfigKeyUnique(SysConfig config);
}
