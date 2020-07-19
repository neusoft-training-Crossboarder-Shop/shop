package neu.train.project.mvo.service.impl;

import neu.train.common.utils.SecurityUtils;
import neu.train.project.mvo.domain.mvoManufacturerExample;
import neu.train.project.mvo.mapper.mvoManufacturerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MvoCommonService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    mvoManufacturerMapper mvoManufacturerMapper;
    public int getManId(){
        long id = SecurityUtils.getLoginUser().getUser().getUserId();
        mvoManufacturerExample mvoManufacturerExample = new mvoManufacturerExample();
        mvoManufacturerExample.createCriteria().andSysUserIdEqualTo(Math.toIntExact(id));
        int manId= mvoManufacturerMapper.selectByExample(mvoManufacturerExample).get(0).getManId();
        return manId;
    }

}
