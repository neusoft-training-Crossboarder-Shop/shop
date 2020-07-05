package neu.train.project.bvo.Service;

import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.bvo.Controller.DropShipperController;
import neu.train.project.bvo.domain.DropShipper;

public interface DropShipperService {
    public AjaxResult getBasicInfo();
    public AjaxResult register(DropShipper dropShipper);
    public AjaxResult update(DropShipper dropShipper);
}
