package neu.train.project.mvo.service.impl;

import neu.train.common.utils.SecurityUtils;
import neu.train.project.bvo.Service.impl.BvoCommonService;
import neu.train.project.mvo.domain.mvoManufacturer;
import neu.train.project.mvo.domain.mvoManufacturerExample;
import neu.train.project.mvo.mapper.mvoManufacturerMapper;
import neu.train.project.mvo.service.IManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements IManufacturerService {

    @Autowired
    BvoCommonService bvoCommonService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    mvoManufacturerMapper mvoManufacturerMapper;
    @Override
    public mvoManufacturer getManufacturer() {
        mvoManufacturerExample mvoManufacturerExample = new mvoManufacturerExample();
        mvoManufacturerExample.createCriteria().andSysUserIdEqualTo(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
        List<mvoManufacturer> mvoManufacturers = mvoManufacturerMapper.selectByExample(mvoManufacturerExample);
        return mvoManufacturers.get(0);
    }

    @Override
    public void insertManufacturer(mvoManufacturer manufacturer) {
        String name =SecurityUtils.getLoginUser().getUsername();
        manufacturer.setCreatedBy(name);
        manufacturer.setLastUpdateBy(name);
        manufacturer.setCreateTime(LocalDateTime.now());
        manufacturer.setLastUpdateTime(LocalDateTime.now());
        mvoManufacturerMapper.insertSelective(manufacturer);
    }

    @Override
    public void updateManufacturer(mvoManufacturer manufacturer) {
        String name =SecurityUtils.getLoginUser().getUsername();
        manufacturer.setLastUpdateBy(name);
        manufacturer.setLastUpdateTime(LocalDateTime.now());
        mvoManufacturerMapper.updateByPrimaryKey(manufacturer);
    }
}
