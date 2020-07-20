package neu.train.project.mvo.service.impl;

import neu.train.common.exception.CustomException;
import neu.train.common.utils.SecurityUtils;
import neu.train.project.mvo.domain.mvoManufacturer;
import neu.train.project.mvo.domain.mvoManufacturerExample;
import neu.train.project.mvo.mapper.mvoManufacturerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MvoCommonService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    mvoManufacturerMapper mvoManufacturerMapper;
    public int getManId(){
        long id = SecurityUtils.getLoginUser().getUser().getUserId();
        mvoManufacturerExample mvoManufacturerExample = new mvoManufacturerExample();
        mvoManufacturerExample.createCriteria().andSysUserIdEqualTo(Math.toIntExact(id));

        List<mvoManufacturer> mvoManufacturers = mvoManufacturerMapper.selectByExample(mvoManufacturerExample);
        if (mvoManufacturers.size() == 0) {
            throw new CustomException("You should finish your personal info in the profile firstly");
        }else{
            return mvoManufacturers.get(0).getManId();
        }

    }

}
