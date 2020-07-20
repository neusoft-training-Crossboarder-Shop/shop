package neu.train.project.mvo.service.impl;

import neu.train.common.exception.CustomException;
import neu.train.common.utils.SecurityUtils;
import neu.train.project.common.CommonController;
import neu.train.project.mvo.domain.*;
import neu.train.project.mvo.domain.vo.MvoSearchProduct;
import neu.train.project.mvo.mapper.*;
import neu.train.project.mvo.service.IMvoProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
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



    @Override
    public List<mvoProduct> getProductList(MvoSearchProduct product) {
        return mvoProductMapper.selectBySearchProduct(product);
    }

    @Override
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
        product.setCreateTime(LocalDateTime.now());
        product.setLastUpdateTime(LocalDateTime.now());

        mvoProductMapper.insert(product);



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
            mvoImage.setUri("/profile/product/2020/07/18/739d0ab61d6626309b822de8f10cb600.jpeg");
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
    public void updateProduct(mvoProduct product) {
        //新建Product对象
        mvoProductMapper.updateByPrimaryKey(product);
        mvoProductCategory mvoProductCategory = product.getCategories().get(0);
        //新建种类
        mvoProductCategoryMapper.updateByPrimaryKey(mvoProductCategory);
        //新建包裹信息
        mvoPackageInfo mvoPackageInfo = product.getProductPackageInfos().get(0);
        mvoPackageInfoMapper.updateByPrimaryKey(mvoPackageInfo);


        String name = SecurityUtils.getLoginUser().getUsername();
        product.setLastUpdateBy(name);
        product.setLastUpdateTime(LocalDateTime.now());


    }

    @Override
    @Transactional
    public void deleteProductByIds(int[] ids){
        for (int id :
                ids) {

            // Delete 种类
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


        }
    }

    @Override
    public boolean uploadProductImage(Integer proId, Integer imgId, Integer typeCd, String imageUrl) {
        mvoImage image = new mvoImage();
        image.setProId(proId);
        image.setUri(imageUrl);
        image.setTypeCd(typeCd);
        if(imgId == null || imgId == 0){
            image.setImgId(null);
            return mvoImageMapper.insertSelective(image) == 1;
        }else{
            //更新缩略图片
            if (mvoImageMapper.selectByPrimaryKey(imgId).getTypeCd()==0){
                return mvoImageMapper.updateByPrimaryKeySelective(image)==1;
            }

            //大图最多12张
            mvoImageExample mvoImageExample = new mvoImageExample();
            mvoImageExample.createCriteria().andProIdEqualTo(proId);
            if (mvoImageMapper.selectByExample(mvoImageExample).size()>=12){
                throw new CustomException("最多上传12张图片");
            }
            //更新大图
            image.setImgId(imgId);
            return mvoImageMapper.updateByPrimaryKey(image)==1;
        }
    }
    @Override
    @Transactional
    public List<mvoProductDescription> getDescriptionByProId(Integer proId){

        mvoProductDescriptionExample mvoProductDescriptionExample = new mvoProductDescriptionExample();
        mvoProductDescriptionExample.createCriteria().andProIdEqualTo(proId);

        List<mvoProductDescription> mvoProductDescriptions = mvoProductDescriptionMapper.selectByExample(mvoProductDescriptionExample);

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
    public void updateDescription(List<mvoProductDescription> productDescriptions){
        for (mvoProductDescription mvoProductDescription:productDescriptions
             ) {
            mvoProductDescriptionMapper.updateByPrimaryKeySelective(mvoProductDescription);
        }
    }

    @Override
    public boolean deleteProductImageById(Integer imgId) {
        return mvoImageMapper.deleteByPrimaryKey(imgId)==1;
    }

    @Override
    public void updateProductProStatus(Integer proId, String status) {
        mvoProduct product = new mvoProduct();
        product.setProId(proId);
        product.setProstate(status);
        mvoProductMapper.updateByPrimaryKeySelective(product);
    }


}

