package neu.train.project.bvo.Service.impl;

import neu.train.common.constant.HttpStatus;
import neu.train.common.utils.SecurityUtils;
import neu.train.framework.redis.RedisCache;
import neu.train.framework.security.LoginUser;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.bvo.Service.DropShipperService;
import neu.train.project.bvo.domain.DropShipper;
import neu.train.project.bvo.domain.DropShipperExample;
import neu.train.project.bvo.mapper.DropShipperMapper;
import neu.train.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class DropShipperServiceImpl implements DropShipperService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    DropShipperMapper dropShipperMapper;
    @Autowired
    RedisCache cache;

    @Override
    public AjaxResult getBasicInfo() {
        SysUser loginUser = SecurityUtils.getLoginUser().getUser();
        DropShipperExample dropShipperExample = new DropShipperExample();
        dropShipperExample.createCriteria().andSysUserIdEqualTo(Math.toIntExact(loginUser.getUserId()));
        List<DropShipper> result = dropShipperMapper.selectByExample(dropShipperExample);
        if (result.size() == 0) {
            return AjaxResult.error(HttpStatus.NO_CONTENT,"No such dropshipper");
        }else{
            return AjaxResult.success(result.get(0));
        }
    }

    @Override
    public AjaxResult register(DropShipper dropShipper) {
        SysUser loginUser = SecurityUtils.getLoginUser().getUser();
        dropShipper.setCreatedBy(loginUser.getUserName());
        dropShipper.setLastUpdateBy(loginUser.getUserName());

        dropShipper.setSysUserId(Math.toIntExact(loginUser.getUserId()));
        int id=dropShipperMapper.insertSelective(dropShipper);

        DropShipperExample dropShipperExample = new DropShipperExample();
        dropShipperExample.createCriteria().andSysUserIdEqualTo(id);
        List<DropShipper> dropShippers = dropShipperMapper.selectByExample(dropShipperExample);

        return AjaxResult.insertSuccess(dropShippers.get(0));
    }

    @Override
    public AjaxResult update(DropShipper dropShipper) {
        dropShipper.setLastUpdateBy(SecurityUtils.getLoginUser().getUser().getUserName());
        int result=dropShipperMapper.updateByPrimaryKeySelective(dropShipper);
        if (result==0){
            return AjaxResult.error("更新Fail");
        }else{
            return AjaxResult.updateSuccess();
        }
    }
}
