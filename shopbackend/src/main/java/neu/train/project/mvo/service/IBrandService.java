package neu.train.project.mvo.service;


import neu.train.project.mvo.domain.mvoBrand;

import java.util.List;

/**
 * 参数配置 服务层
 *
 * @author
 */
public interface IBrandService
{
    /**
     * 查询参数配置信息

     */
    public mvoBrand selectBrandByBrandId(int storeId);


    /**
     *
     * 查询列表
     * @return
     *
     */
    public List<mvoBrand> getBrandList(mvoBrand brand);

    public List<mvoBrand> getBrandList();

    /**
     * 新增
     *
     * @param store
     * @return 结果
     */
    public int insertBrand(mvoBrand brand);

    /**
     * 修改
     *
     * @param store 参数
     * @return 结果
     */
    public int updateBrand(mvoBrand brand);

    /**
     * 批量删除
     *
     * @param storeId 需要删除的参数ID
     * @return 结果
     */
    public int deleteBrandByIds(int[] brandId);


    public boolean updateBrandImage(Integer brdId, String imageUrl);
    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数信息
     * @return 结果
     */
}

