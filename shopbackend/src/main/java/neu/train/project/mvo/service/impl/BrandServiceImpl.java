package neu.train.project.mvo.service.impl;

import neu.train.common.utils.SecurityUtils;
import neu.train.common.utils.StringUtils;
import neu.train.project.mvo.domain.mvoBrand;
import neu.train.project.mvo.domain.mvoBrandExample;
import neu.train.project.mvo.mapper.mvoBrandMapper;
import neu.train.project.mvo.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    MvoCommonService mvoCommonService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    mvoBrandMapper mvoBrandMapper;



    @Override
    public mvoBrand selectBrandByBrandId(int brandId) {
        return mvoBrandMapper.selectByPrimaryKey(brandId);
    }

    @Override
    public List<mvoBrand> getBrandList(mvoBrand brand) {
        int id = mvoCommonService.getManId();
        brand.setManId(id);
        mvoBrandExample mvoBrandExample = new mvoBrandExample();

        neu.train.project.mvo.domain.mvoBrandExample.Criteria criteria = mvoBrandExample.createCriteria().andManIdEqualTo(id);
        if (StringUtils.isNotEmpty(brand.getNameCn())) {
            criteria.andNameCnLike(brand.getNameCn());
        }
        if (StringUtils.isNotEmpty(brand.getNameEn())) {
            criteria.andNameEnLike(brand.getNameEn());
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

    @Override
    public int insertBrand(mvoBrand brand) {
        String name =SecurityUtils.getLoginUser().getUsername();
        brand.setCreatedBy(name);
        brand.setLastUpdateBy(name);
        brand.setCreateTime(LocalDateTime.now());
        brand.setLastUpdateTime(LocalDateTime.now());
        brand.setManId(mvoCommonService.getManId());
        return mvoBrandMapper.insertSelective(brand);
    }

    @Override
    public int updateBrand(mvoBrand brand) {
        brand.setLastUpdateBy(SecurityUtils.getLoginUser().getUsername());
        brand.setLastUpdateTime(LocalDateTime.now());
        return mvoBrandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int deleteBrandByIds(int[] brandIds) {
        return mvoBrandMapper.deleteBrandByIds(brandIds);
    }

    @Override
    public void clearCache() {

    }

    @Override
    public boolean updateBrandImage(Integer brdId, String imageUrl) {
        mvoBrand mvoBrand = new mvoBrand();
        mvoBrand.setBrdId(brdId);
        mvoBrand.setPicUrl(imageUrl);
        return mvoBrandMapper.updateByPrimaryKeySelective(mvoBrand)>0;
    }
}
