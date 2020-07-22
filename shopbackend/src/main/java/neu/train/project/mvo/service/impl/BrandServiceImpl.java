package neu.train.project.mvo.service.impl;

import neu.train.common.utils.SecurityUtils;
import neu.train.common.utils.StringUtils;
import neu.train.framework.redis.RedisCache;
import neu.train.project.mvo.domain.mvoBrand;
import neu.train.project.mvo.domain.mvoBrandExample;
import neu.train.project.mvo.mapper.mvoBrandMapper;
import neu.train.project.mvo.service.IBrandService;
import neu.train.project.mvo.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static neu.train.project.mvo.util.AdminResponseCode.BRAND_NAME_EXIST;

@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    MvoCommonService mvoCommonService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    mvoBrandMapper mvoBrandMapper;
    @Autowired
    RedisCache redisCache;
    public static final String BRAND_CACHE_PREFIX = "BRAND:";



    @Override
    @Cacheable(value = BRAND_CACHE_PREFIX)
    public mvoBrand selectBrandByBrandId(int brandId) {

        mvoBrand mvoBrand = mvoBrandMapper.selectByPrimaryKey(brandId);

        return mvoBrand;
    }

    @Override
    public List<mvoBrand> getBrandList(mvoBrand brand) {
        int id = mvoCommonService.getManId();
        brand.setManId(id);
        mvoBrandExample mvoBrandExample = new mvoBrandExample();

        neu.train.project.mvo.domain.mvoBrandExample.Criteria criteria = mvoBrandExample.createCriteria().andManIdEqualTo(id);
        if (StringUtils.isNotEmpty(brand.getNameCn())) {
            criteria.andNameCnLike("%" + brand.getNameCn() + "%");
        }
        if (StringUtils.isNotEmpty(brand.getNameEn())) {
            criteria.andNameEnLike("%" + brand.getNameEn() + "%");
        }
        if (brand.getBrdId()!=null){
            criteria.andBrdIdEqualTo(brand.getBrdId());
        }

        return mvoBrandMapper.selectByExample(mvoBrandExample);
    }

    @Override
    public List<mvoBrand> getBrandList() {
        int id=mvoCommonService.getManId();
        mvoBrandExample mvoBrandExample = new mvoBrandExample();
        mvoBrandExample.createCriteria().andManIdEqualTo(id);
        return mvoBrandMapper.selectByExample(mvoBrandExample);
    }


    public boolean checkExistByNameCn(String name) {
        mvoBrandExample example = new mvoBrandExample();
        example.or().andNameCnEqualTo(name).andDeletedEqualTo(false);
        return mvoBrandMapper.countByExample(example) != 0;
    }

    public boolean checkExistByNameEn(String name) {
        mvoBrandExample example = new mvoBrandExample();
        example.or().andNameEnEqualTo(name).andDeletedEqualTo(false);
        return mvoBrandMapper.countByExample(example) != 0;
    }


    @Override
    public int insertBrand(mvoBrand brand) {
        String nameCn = brand.getNameCn();
        String nameEn = brand.getNameEn();

        //判断是否重名
        if (checkExistByNameCn(nameCn)&checkExistByNameEn(nameEn)) {
            return (int) ResponseUtil.fail(BRAND_NAME_EXIST, "The same name already exist!");
        }else {

            String name =SecurityUtils.getLoginUser().getUsername();
            //获取当前User 名
            brand.setCreatedBy(name);
            brand.setLastUpdateBy(name);
            brand.setManId(mvoCommonService.getManId());
            //默认图片
            brand.setPicUrl("/profile/brand/2020/07/22/e400edf79513250747504b3020700878.jpeg");
            //新建一个图片对象

            return mvoBrandMapper.insertSelective(brand);
            //不重名的话complete Add
        }
        //新建一个图片对象


    }

    @Override
    @CacheEvict(value = BRAND_CACHE_PREFIX,key="#brand.getBrdId()")
    public int updateBrand(mvoBrand brand) {
        brand.setLastUpdateBy(SecurityUtils.getLoginUser().getUsername());
        return mvoBrandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int deleteBrandByIds(int[] brandIds) {
        for (Integer id : brandIds) {
            redisCache.deleteObject(BRAND_CACHE_PREFIX + id);
        }
        return mvoBrandMapper.deleteBrandByIds(brandIds);
    }


    @Override
    public boolean updateBrandImage(Integer brdId, String imageUrl) {
        mvoBrand mvoBrand = new mvoBrand();
        mvoBrand.setBrdId(brdId);
        mvoBrand.setPicUrl(imageUrl);
        return mvoBrandMapper.updateByPrimaryKeySelective(mvoBrand)>0;
    }
}
