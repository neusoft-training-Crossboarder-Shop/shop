package neu.train.project.order.service;

import neu.train.project.order.pojo.ShaShippingAddress;
import neu.train.project.order.pojo.StiStoreDropshipItem;
import neu.train.project.order.pojo.StoStoreOrder;
import neu.train.project.order.vo.*;

import java.util.List;

public interface OrderService {


    List<SendASimpleSto> selectStoStrProByTime(GetAStoOrderQueryByTime getAStoOrderQueryByTime);
    SendAStoOrder selectStoStrProById(int stoId);
    StoStoreOrder selectStoById(int stoId);
    boolean updateStoById(String updateBy,GetUpdateASto getUpdateASto);
    boolean insertOneAddress(ShaShippingAddress shaShippingAddress);
    boolean updateOneAddress(ShaShippingAddress shaShippingAddress);
    ShaShippingAddress selectAddressByStoId(int stoId);
    boolean pay(int bvoId, GetAPayMessage getAPayMessage);
    SendComplexSao selectSaoStrProByStoId(int stoId);
    List<SendComplexSao> selectSaoStrProByTime(GetASaoQueryByTime getASaoQueryByTime);
    boolean updateStoStatus(GetStoStatusQuery getStoStatusQuery);
    boolean insertStos(int bvoId,GetPurchaseMessage getPurchaseMessage);
    boolean acceptSto(int bvoId,int stoId);
    boolean insertDropItem(int bvoId,int stoId);
    boolean onOffShelf(int someoneId,int dilId,String status);
    StiStoreDropshipItem selectStiRedisById(int dilId);
    boolean updateShelfPriceAmount(int someoneId,GetShelfPriceAmount getShelfPriceAmount);
    List<SendSti> selectSTIs(GetStiQuery getStiQuery);
}
