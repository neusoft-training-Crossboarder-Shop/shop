package neu.train.project.bvo.Service.impl;

import neu.train.common.utils.SecurityUtils;
import neu.train.framework.security.LoginUser;
import neu.train.project.bvo.domain.DropShipperExample;
import neu.train.project.bvo.mapper.DropShipperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BvoCommonService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    DropShipperMapper dropShipperMapper;
    public int getDsrId(){
        long id = SecurityUtils.getLoginUser().getUser().getUserId();
        DropShipperExample dropShipperExample = new DropShipperExample();
        dropShipperExample.createCriteria().andSysUserIdEqualTo(Math.toIntExact(id));
        int dsrId=dropShipperMapper.selectByExample(dropShipperExample).get(0).getDsrId();
        return dsrId;
    }

}
