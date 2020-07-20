package neu.train.common.utils;

import neu.train.common.constant.Constants;
import neu.train.common.utils.spring.SpringUtils;
import neu.train.framework.redis.RedisCache;
import neu.train.project.system.domain.SysDictData;

import java.util.Collection;
import java.util.List;

/**
 * Dict   工具类
 *
 * @author
 */
public class DictUtils
{
    /**
     * 设置Dict   缓存
     *
     * @param key 参数键
     * @param dictDatas Dict   Data 列表
     */
    public static void setDictCache(String key, List<SysDictData> dictDatas)
    {
        SpringUtils.getBean(RedisCache.class).setCacheObject(getCacheKey(key), dictDatas);
    }

    /**
     * 获取Dict   缓存
     *
     * @param key 参数键
     * @return dictDatas Dict   Data 列表
     */
    public static List<SysDictData> getDictCache(String key)
    {
        Object cacheObj = SpringUtils.getBean(RedisCache.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj))
        {
            List<SysDictData> DictDatas = StringUtils.cast(cacheObj);
            return DictDatas;
        }
        return null;
    }

    /**
     * 清空Dict   缓存
     */
    public static void clearDictCache()
    {
        Collection<String> keys = SpringUtils.getBean(RedisCache.class).keys(Constants.SYS_DICT_KEY + "*");
        SpringUtils.getBean(RedisCache.class).deleteObject(keys);
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey)
    {
        return Constants.SYS_DICT_KEY + configKey;
    }
}
