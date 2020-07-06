package neu.train.project.mvo.brand.service.implement;

import neu.train.project.mvo.brand.mapper.ManManufacturerMapper;
import neu.train.project.mvo.brand.pojo.ManManufacturer;
import neu.train.project.mvo.brand.pojo.ManManufacturerExample;
import neu.train.project.mvo.brand.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyServiceImp implements CompanyService {
    @Autowired
    ManManufacturerMapper manManufacturerMapper;

    @Override
    @Cacheable(value="company",key="#sysUserId")
    public List<ManManufacturer> listCompany(int sysUserId) {
        ManManufacturerExample manManufacturerExample = new ManManufacturerExample();
        ManManufacturerExample.Criteria manManufacturerExampleCriteria=manManufacturerExample.createCriteria();
        manManufacturerExampleCriteria.andSysUserIdEqualTo(sysUserId);
        return manManufacturerMapper.selectByExampleWithBLOBs(manManufacturerExample);
    }
}
