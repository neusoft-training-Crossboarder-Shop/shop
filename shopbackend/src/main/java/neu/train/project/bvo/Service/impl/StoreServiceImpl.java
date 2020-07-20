package neu.train.project.bvo.Service.impl;

import neu.train.common.utils.SecurityUtils;
import neu.train.common.utils.StringUtils;
import neu.train.project.bvo.Service.StoreService;
import neu.train.project.bvo.domain.Store;
import neu.train.project.bvo.domain.StoreExample;
import neu.train.project.bvo.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StoreServiceImpl implements StoreService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    StoreMapper storeMapper ;

    @Autowired
    BvoCommonService bvoCommonService;
    @Override
    public Store selectStoreByStoreId(Long storeId) {
        return storeMapper.selectByPrimaryKey(Math.toIntExact(storeId));
    }

    @Override
    public List<Store> getStoreList(Store store) {
        int id = bvoCommonService.getDsrId();
        store.setDsrId(id);
        StoreExample storeExample = new StoreExample();

        StoreExample.Criteria criteria = storeExample.createCriteria().andDsrIdEqualTo(id);
        if (StringUtils.isNotEmpty(store.getStoreName())) {
            criteria.andStoreNameLike(store.getStoreName());
        }
        if (store.getStoreId()!=null){
            criteria.andStoreIdEqualTo(store.getStoreId());
        }
        if (store.getPlatformType()!=null){
            criteria.andPlatformTypeEqualTo(store.getPlatformType());
        }

        List<Store> stores = storeMapper.selectByExample(storeExample);
        return stores;
    }

    @Override
    public int insertStore(Store store) {

        String name =SecurityUtils.getLoginUser().getUsername();
        store.setCreatedBy(name);
        store.setLastUpdateBy(name);
        store.setDsrId(bvoCommonService.getDsrId());
        return storeMapper.insertSelective(store);
    }

    @Override
    public int updateStore(Store store) {
        store.setLastUpdateBy(SecurityUtils.getLoginUser().getUsername());
        return storeMapper.updateByPrimaryKeySelective(store);
    }

    @Override
    public int deleteStoreByIds(int[] storeId) {
        return storeMapper.deleteStoreByIds(storeId);
    }

    @Override
    public void clearCache() {

    }
}
