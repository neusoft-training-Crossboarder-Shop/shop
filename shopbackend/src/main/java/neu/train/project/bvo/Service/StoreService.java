package neu.train.project.bvo.Service;


import neu.train.project.bvo.domain.Store;

import java.util.List;

/**
 * 参数配置 服务层
 *
 * @author
 */
public interface StoreService
{
    /**
     * 查询参数配置信息

     */
    public Store selectStoreByStoreId(Long storeId);


    /**
     *
     * 查询列表
     * @return
     *
     */
    public List<Store> getStoreList(Store store);

    /**
     * 新增
     *
     * @param store
     * @return 结果
     */
    public int insertStore(Store store);

    /**
     * 修改
     *
     * @param store 参数
     * @return 结果
     */
    public int updateStore(Store store);

    /**
     * 批量删除
     *
     * @param storeId 需要删除的参数ID
     * @return 结果
     */
    public int deleteStoreByIds(int[] storeId);

    /**
     * 清空缓存数据
     */
    public void clearCache();

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数信息
     * @return 结果
     */
}

