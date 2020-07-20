package neu.train.project.bvo.Service.impl;

import neu.train.common.exception.CustomException;
import neu.train.common.utils.SecurityUtils;
import neu.train.framework.security.LoginUser;
import neu.train.project.bvo.domain.DropShipper;
import neu.train.project.bvo.domain.DropShipperExample;
import neu.train.project.bvo.mapper.DropShipperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BvoCommonService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    DropShipperMapper dropShipperMapper;
    public int getDsrId(){
        long id = SecurityUtils.getLoginUser().getUser().getUserId();
        DropShipperExample dropShipperExample = new DropShipperExample();
        dropShipperExample.createCriteria().andSysUserIdEqualTo(Math.toIntExact(id));

        List<DropShipper> dropShippers = dropShipperMapper.selectByExample(dropShipperExample);
        if (dropShippers.size() == 0) {
            throw new CustomException("You should finish your personal info in the profile firstly");
        }else{
            return dropShippers.get(0).getDsrId();
        }

    }

}
