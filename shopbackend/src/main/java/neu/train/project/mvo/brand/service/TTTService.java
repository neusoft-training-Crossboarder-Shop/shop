package neu.train.project.mvo.brand.service;

import neu.train.project.mvo.brand.mapper.ManManufacturerMapper;
import neu.train.project.mvo.brand.pojo.ManManufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TTTService {
    @Autowired
    private ManManufacturerMapper manManufacturerMapper;

    @Cacheable(value = "shabbb",key="#manId")
    public ManManufacturer test7(int manId){
        //return manManufacturerMapper.selectByPrimaryKey(6);
        return new ManManufacturer();
    }

}