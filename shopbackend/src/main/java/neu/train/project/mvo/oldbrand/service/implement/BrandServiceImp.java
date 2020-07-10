package neu.train.project.mvo.oldbrand.service.implement;

import neu.train.framework.redis.RedisCache;
import neu.train.project.mvo.oldbrand.mapper.BrdBrandMapper;
import neu.train.project.mvo.oldbrand.mapper.ManManufacturerMapper;
import neu.train.project.mvo.oldbrand.pojo.BrdBrand;
import neu.train.project.mvo.oldbrand.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BrandServiceImp implements BrandService {

    @Autowired
    BrdBrandMapper brdBrandMapper;
    @Autowired
    ManManufacturerMapper manManufacturerMapper;
    @Autowired
    RedisCache redisCache;

    @Override
    public BrdBrand createBrand(HashMap<String,Object> data) {
        return null;
    }
}
