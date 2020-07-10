package neu.train.project.mvo.oldbrand.service.implement;

import neu.train.project.mvo.oldbrand.mapper.ManManufacturerMapper;
import neu.train.project.mvo.oldbrand.pojo.ManManufacturer;
import neu.train.project.mvo.oldbrand.pojo.ManManufacturerExample;
import neu.train.project.mvo.oldbrand.service.CompanyService;
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
