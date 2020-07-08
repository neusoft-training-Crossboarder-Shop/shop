package neu.train.project.mvo.brand.service.implement;

import neu.train.framework.redis.RedisCache;
import neu.train.project.mvo.brand.mapper.BrdBrandMapper;
import neu.train.project.mvo.brand.mapper.ManManufacturerMapper;
import neu.train.project.mvo.brand.pojo.BrdBrand;
import neu.train.project.mvo.brand.pojo.ManManufacturer;
import neu.train.project.mvo.brand.service.BrandService;
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
