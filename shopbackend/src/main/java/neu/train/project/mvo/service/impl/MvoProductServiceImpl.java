package neu.train.project.mvo.service.impl;

import neu.train.common.exception.CustomException;
import neu.train.common.utils.SecurityUtils;
import neu.train.framework.redis.RedisCache;
import neu.train.project.common.CommonController;
import neu.train.project.mvo.domain.*;
import neu.train.project.mvo.domain.vo.MvoSearchProduct;
import neu.train.project.mvo.mapper.*;
import neu.train.project.mvo.service.IMvoProductService;
import neu.train.project.system.domain.vo.MetaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.List;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class MvoProductServiceImpl implements IMvoProductService {

    @Autowired
    mvoProductMapper mvoProductMapper;
    @Autowired
    mvoProductDescriptionMapper mvoProductDescriptionMapper;
    @Autowired
    mvoPackageInfoMapper mvoPackageInfoMapper;
    @Autowired
    mvoProductCategoryMapper mvoProductCategoryMapper;
    @Autowired
    mvoImageMapper mvoImageMapper;
    @Autowired
    MvoCommonService commonService;
    @Autowired
    RedisCache redisCache;
    public static final String PRO_CACHE_PREFIX = "PRODUCT:";
    public static final String PRO_DESCRI_CACHE_PREFIX = "PRODUCT_DESCRIPTION:";


    @Override
    public List<mvoProduct> getProductList(MvoSearchProduct product) {
        product.setManId(commonService.getManId());
        return mvoProductMapper.selectBySearchProduct(product);

    }

    @Override
    @Cacheable(value =PRO_CACHE_PREFIX)
    public mvoProduct getProductByProId(Integer id) {
        return mvoProductMapper.selectByProId(id);
    }

    @Override
    @Transactional
    public void insertProduct(mvoProduct product) {
        //新建Product对象



        product.setManId(commonService.getManId());

        String name = SecurityUtils.getLoginUser().getUsername();
        product.setCreatedBy(name);
        product.setLastUpdateBy(name);
        product.setProstate("Off Shelf");
        mvoProductMapper.insertSelective(product);



        int id = product.getProId();


        //新建一个类别
        for (mvoProductCategory mvoProductCategory :
             product.getCategories()) {
            mvoProductCategory.setProId(id);
            //新建种类
            mvoProductCategoryMapper.insert(mvoProductCategory);
        }

        //新建一个包裹
        for (mvoPackageInfo mvoPackageInfo :
                product.getProductPackageInfos()) {
            mvoPackageInfo.setProId(id);
            //新建种类
            mvoPackageInfoMapper.insert(mvoPackageInfo);
        }

        //新建一个图片对象
        for (mvoImage mvoImage :
                product.getImages()) {
            mvoImage.setProId(id);
            //默认图片
            mvoImage.setUri("/profile/brand/2020/07/22/e400edf79513250747504b3020700878.jpeg");
            mvoImageMapper.insertSelective(mvoImage);
        }
    }

    @Override
    @Transactional
    public List<mvoImage> getImagesByProId(Integer proId) {

        mvoImageExample mvoImageExample = new mvoImageExample();
        mvoImageExample.createCriteria().andProIdEqualTo(proId);
        return mvoImageMapper.selectByExample(mvoImageExample);

    }

    @Override
    @Transactional
    @CacheEvict(value = PRO_CACHE_PREFIX,key = "#product.getProId()")
    public void updateProduct(mvoProduct product) {
        //新建Product对象
        mvoProductMapper.updateByPrimaryKeySelective(product);
        mvoProductCategory mvoProductCategory = product.getCategories().get(0);
        //新建种类
        mvoProductCategoryMapper.updateByPrimaryKeySelective(mvoProductCategory);
        //新建包裹信息
        mvoPackageInfo mvoPackageInfo = product.getProductPackageInfos().get(0);

        mvoPackageInfoMapper.updateByPrimaryKeySelective(mvoPackageInfo);


        String name = SecurityUtils.getLoginUser().getUsername();
        product.setLastUpdateBy(name);


    }

    @Override
    @Transactional
    public void deleteProductByIds(int[] ids){
        for (int id :
                ids) {
            redisCache.deleteObject(PRO_CACHE_PREFIX + id);
            //删除种类

            mvoProductCategoryExample mvoProductCategoryExample = new mvoProductCategoryExample();
            mvoProductCategoryExample.createCriteria().andProIdEqualTo(id);
            mvoProductCategoryMapper.deleteByExample(mvoProductCategoryExample);

            // Delete 包裹
            mvoPackageInfoExample mvoPackageInfoExample = new mvoPackageInfoExample();
            mvoPackageInfoExample.createCriteria().andProIdEqualTo(id);
            mvoPackageInfoMapper.deleteByExample(mvoPackageInfoExample);

            // Delete 图片
            mvoImageExample mvoImageExample = new mvoImageExample();
            mvoImageExample.createCriteria().andProIdEqualTo(id);
            mvoImageMapper.deleteByExample(mvoImageExample);

            // Delete description
            mvoProductDescriptionExample mvoProductDescriptionExample = new mvoProductDescriptionExample();
            mvoProductDescriptionExample.createCriteria().andProIdEqualTo(id);
            mvoProductDescriptionMapper.deleteByExample(mvoProductDescriptionExample);

            //删除自己
            mvoProductMapper.deleteByPrimaryKey(id);

        }
    }

    @Override
    @Transactional
    public boolean uploadProductImage(Integer proId, Integer imgId, Integer typeCd, String imageUrl) {
        mvoImage image = new mvoImage();
        image.setProId(proId);
        image.setUri(imageUrl);
        image.setTypeCd(typeCd);

        if(imgId == null || imgId == 0){
            //如果是新图片

//            1.不能上传12张图片
            mvoImageExample mvoImageExample1 = new mvoImageExample();
            mvoImageExample1.createCriteria().andProIdEqualTo(proId);
            if (mvoImageMapper.selectByExample(mvoImageExample1).size()>=12){
                throw new CustomException("最多Upload 12张图片");
            }
//            2.如果添加的是缩略图则进入下架状态
            if (typeCd == 0) {
                mvoProduct product = new mvoProduct();
                product.setProId(proId);
                product.setProstate("Off Shelf");
                mvoProductMapper.updateByPrimaryKeySelective(product);
            }

            image.setImgId(null);
            return mvoImageMapper.insertSelective(image) == 1;
        }else{
            image.setImgId(imgId);
            //更新图片
            if (mvoImageMapper.selectByPrimaryKey(imgId).getTypeCd()==0){
                //如果更新缩略图 删除当前的缓存
                redisCache.deleteObject(PRO_CACHE_PREFIX+proId);
                return mvoImageMapper.updateByPrimaryKeySelective(image)==1;
            }
            //更新大图
            return mvoImageMapper.updateByPrimaryKeySelective(image)==1;
        }
    }
    @Override
    @Transactional
    @Cacheable(value = PRO_DESCRI_CACHE_PREFIX)
    public List<mvoProductDescription> getDescriptionByProId(Integer proId){

        mvoProductDescriptionExample mvoProductDescriptionExample = new mvoProductDescriptionExample();
        mvoProductDescriptionExample.createCriteria().andProIdEqualTo(proId);

        List<mvoProductDescription> mvoProductDescriptions = mvoProductDescriptionMapper.selectByExampleWithBLOBs(mvoProductDescriptionExample);

        if (mvoProductDescriptions.size() == 0) {
            mvoProductDescription mvoProductDescription = new mvoProductDescription();
            mvoProductDescription.setProId(proId);
            mvoProductDescription.setTypeCd("RichText");
            mvoProductDescription.setPlatformType("amazon");
            mvoProductDescriptionMapper.insertSelective(mvoProductDescription);
            mvoProductDescription.setPlatformType("ebay");
            mvoProductDescriptionMapper.insertSelective(mvoProductDescription);
            return mvoProductDescriptionMapper.selectByExample(mvoProductDescriptionExample);
        }
        return mvoProductDescriptions;
    }

    @Override
    @Transactional
    @CacheEvict(value = PRO_DESCRI_CACHE_PREFIX,key = "#productDescriptions.get(0).getProId()")
    public void updateDescription(List<mvoProductDescription> productDescriptions){
        for (mvoProductDescription mvoProductDescription:productDescriptions
             ) {
            mvoProductDescriptionMapper.updateByPrimaryKeySelective(mvoProductDescription);
        }
    }

    @Override
    public boolean deleteProductImageById(Integer imgId) {
        int proId=mvoImageMapper.selectByPrimaryKey(imgId).getProId();
        redisCache.deleteObject(PRO_CACHE_PREFIX + proId);

        mvoImage mvoImage = mvoImageMapper.selectByPrimaryKey(imgId);

        int type_cd = mvoImage.getTypeCd();

        mvoImageExample mvoImageExample = new mvoImageExample();
        mvoImageExample.createCriteria().andProIdEqualTo(proId).andTypeCdEqualTo(type_cd);
        ;
        List<neu.train.project.mvo.domain.mvoImage> mvoImages = mvoImageMapper.selectByExample(mvoImageExample);

        if (mvoImages.size() == 1) {
            throw new CustomException("It's required at least 1 small/big picture");
        }



        return mvoImageMapper.deleteByPrimaryKey(imgId)==1;
    }

    @Override
    @CacheEvict(value = PRO_CACHE_PREFIX,key = "#proId")
    public void updateProductProStatus(Integer proId, String status) {
        mvoProduct product = new mvoProduct();
        product.setProId(proId);
        product.setProstate(status);


        if (status.equals("In Shelf")){
            //如果缩略图的数量大于1 不能上架
            mvoImageExample mvoImageExample1 = new mvoImageExample();
            mvoImageExample1.createCriteria().andProIdEqualTo(proId).andTypeCdEqualTo(0);
            List<mvoImage> mvoImages1 = mvoImageMapper.selectByExample(mvoImageExample1);
            if (mvoImages1.size() != 1) {
                throw new CustomException("It's required to only one small picture");
            }

            mvoImageExample mvoImageExample2 = new mvoImageExample();
            mvoImageExample2.createCriteria().andProIdEqualTo(proId).andTypeCdEqualTo(1);
            List<mvoImage> mvoImages2 = mvoImageMapper.selectByExample(mvoImageExample2);

            //如果没有一张大图     也不能上架
            if (mvoImages2.size() == 0) {
                throw new CustomException("It's required at least one big picture");
            }
        }



        mvoProductMapper.updateByPrimaryKeySelective(product);
    }
}

