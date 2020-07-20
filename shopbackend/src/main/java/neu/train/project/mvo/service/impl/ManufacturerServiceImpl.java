package neu.train.project.mvo.service.impl;

import neu.train.common.utils.SecurityUtils;
import neu.train.framework.redis.RedisCache;
import neu.train.framework.security.LoginUser;
import neu.train.project.bvo.Service.impl.BvoCommonService;
import neu.train.project.mvo.domain.mvoManufacturer;
import neu.train.project.mvo.domain.mvoManufacturerExample;
import neu.train.project.mvo.mapper.mvoManufacturerMapper;
import neu.train.project.mvo.service.IManufacturerService;
import neu.train.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements IManufacturerService {

    @Autowired
    BvoCommonService bvoCommonService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    mvoManufacturerMapper mvoManufacturerMapper;

    @Autowired
    RedisCache redisCache;

    public static final String MAN_CACHE_PREFIX = "MANUFACTURER:";

    @Override
    public mvoManufacturer getManufacturer() {
        mvoManufacturerExample mvoManufacturerExample = new mvoManufacturerExample();

        int userId = Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId());

        //检查缓存
        mvoManufacturer manufacturer=redisCache.getCacheObject(MAN_CACHE_PREFIX + userId);
        if (manufacturer != null) {
            return manufacturer;
        }

        mvoManufacturerExample.createCriteria().andSysUserIdEqualTo(userId);
        List<mvoManufacturer> mvoManufacturers = mvoManufacturerMapper.selectByExample(mvoManufacturerExample);


        if (mvoManufacturers.size() == 0) {

            return new mvoManufacturer();
        }else{
            mvoManufacturer mvoManufacturer = mvoManufacturers.get(0);
            redisCache.setCacheObject(MAN_CACHE_PREFIX + userId, mvoManufacturer);
            return mvoManufacturer;
        }
    }

    @Override
    public void insertManufacturer(mvoManufacturer manufacturer) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        manufacturer.setManId(Math.toIntExact(loginUser.getUser().getUserId()));
        manufacturer.setCreatedBy(loginUser.getUsername());
        manufacturer.setLastUpdateBy(loginUser.getUsername());
        mvoManufacturerMapper.insertSelective(manufacturer);
    }

    @Override
    public void updateManufacturer(mvoManufacturer manufacturer) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        redisCache.deleteObject(MAN_CACHE_PREFIX+user.getUserId());
        manufacturer.setLastUpdateBy(user.getUserName());
        mvoManufacturerMapper.updateByPrimaryKey(manufacturer);
    }
}
