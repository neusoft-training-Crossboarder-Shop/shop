package neu.train.project.order.service;

import neu.train.project.order.pojo.ShaShippingAddress;
import neu.train.project.order.pojo.StoStoreOrder;
import neu.train.project.order.vo.GetAStoOrderQueryByTime;
import neu.train.project.order.vo.GetUpdateASto;
import neu.train.project.order.vo.SendASimpleSto;
import neu.train.project.order.vo.SendAStoOrder;

import java.util.List;

public interface OrderService {


    List<SendASimpleSto> selectStoStrProByTime(GetAStoOrderQueryByTime getAStoOrderQueryByTime);
    SendAStoOrder selectStoStrProById(int stoId);
    StoStoreOrder selectStoById(int stoId);
    boolean updateStoById(String updateBy,GetUpdateASto getUpdateASto);
    boolean insertOneAddress(ShaShippingAddress shaShippingAddress);
    boolean updateOneAddress(ShaShippingAddress shaShippingAddress);
    ShaShippingAddress selectAddressByStoId(int stoId);
}
