package neu.train.project.order.service.imp;


import neu.train.common.core.lang.UUID;
import neu.train.framework.redis.RedisCache;
import neu.train.project.order.mapper.*;
import neu.train.project.order.pojo.*;
import neu.train.project.order.service.OrderService;
import neu.train.project.order.vo.*;
import neu.train.project.wallet.pojo.WaaWalletAccount;
import neu.train.project.wallet.pojo.WafWalletAccountFund;
import neu.train.project.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    WalletService walletService;
    @Autowired
    RedisCache redisCache;
    @Autowired
    StrStoreMapper strStoreMapper;
    @Autowired
    StoStoreOrderMapper stoStoreOrderMapper;
    @Autowired
    ProProductMapper proProductMapper;
    @Autowired
    ShaShippingAddressMapper shaShippingAddressMapper;
    @Resource(name="orderManMapper")
    ManManufacturerMapper manManufacturerMapper;
    @Autowired
    SaoSalesOrderMapper saoSalesOrderMapper;

    //根据stoId起始终止时间模糊查询StoStrPro
    @Override
    public List<SendASimpleSto> selectStoStrProByTime(GetAStoOrderQueryByTime getAStoOrderQueryByTime) {
        return stoStoreOrderMapper.selectStoStrProByMany(getAStoOrderQueryByTime);
    }

    //根据stoId精确查询单个StoStrPro信息，缓存"stoById:"+stoId
    @Override
    public SendAStoOrder selectStoStrProById(int stoId) {
        StoStoreOrder stoStoreOrder = selectStoById(stoId);
        StrStore strStore = strStoreMapper.selectByPrimaryKey(stoStoreOrder.getStoreId());
        ProProduct proProduct = proProductMapper.selectByPrimaryKey(stoStoreOrder.getProid());
        return new SendAStoOrder(stoStoreOrder, strStore, proProduct);
    }


    //根据stoId精确查询单个Sto信息，缓存"stoById:"+stoId
    @Override
    public StoStoreOrder selectStoById(int stoId) {
        StoStoreOrder stoStoreOrder = redisCache.getCacheObject("stoById:" + stoId);
        if (stoStoreOrder != null) {
            return stoStoreOrder;
        }
        stoStoreOrder = stoStoreOrderMapper.selectByPrimaryKey(stoId);
        redisCache.setCacheObject("stoById:" + stoId, stoStoreOrder);
        return stoStoreOrder;
    }

    //根据stoId更新单个sto信息,其实就是更新个商品数量而已，缓存"stoById:"+stoId
    @Override
    public boolean updateStoById(String bvoId, GetUpdateASto getUpdateASto) {
        StoStoreOrder stoStoreOrder = selectStoById(getUpdateASto.getStoId());
        stoStoreOrder.setUpdateBy(bvoId);
        stoStoreOrder.setQty(getUpdateASto.getQty());
        if (stoStoreOrderMapper.updateByPrimaryKeySelective(stoStoreOrder) == 0) {
            return false;
        } else {
            stoStoreOrder = stoStoreOrderMapper.selectByPrimaryKey(stoStoreOrder.getStoId());
            redisCache.setCacheObject("stoById:" + stoStoreOrder.getStoId(), stoStoreOrder);
            return true;
        }
    }

    //插入一条收获地址，缓存"addressByStoId:"+stoId
    @Override
    public boolean insertOneAddress(ShaShippingAddress shaShippingAddress) {
        shaShippingAddressMapper.insertSelective(shaShippingAddress);
        redisCache.setCacheObject("addressByStoId:" + shaShippingAddress.getStoId(), shaShippingAddress);
        return true;
    }

    //更新一条收获地址，缓存"addressById:"+stoId
    @Override
    public boolean updateOneAddress(ShaShippingAddress shaShippingAddress) {
        ShaShippingAddressExample shaShippingAddressExample = new ShaShippingAddressExample();
        ShaShippingAddressExample.Criteria shaShippingAddressExampleCriteria = shaShippingAddressExample.createCriteria();
        shaShippingAddressExampleCriteria.andStoIdEqualTo(shaShippingAddress.getStoId());
        shaShippingAddressMapper.updateByExampleSelective(shaShippingAddress, shaShippingAddressExample);
        shaShippingAddress = shaShippingAddressMapper.selectByExample(shaShippingAddressExample).get(0);
        redisCache.setCacheObject("addressByStoId:" + shaShippingAddress.getStoId(), shaShippingAddress);
        return true;
    }

    //查询一条收获地址，缓存"addressById:"+stoId
    @Override
    public ShaShippingAddress selectAddressByStoId(int stoId) {
        ShaShippingAddress shaShippingAddress = redisCache.getCacheObject("addressByStoId:" + stoId);
        if (shaShippingAddress != null) {
            return shaShippingAddress;
        }
        ShaShippingAddressExample shaShippingAddressExample = new ShaShippingAddressExample();
        ShaShippingAddressExample.Criteria shaShippingAddressExampleCriteria = shaShippingAddressExample.createCriteria();
        shaShippingAddressExampleCriteria.andStoIdEqualTo(stoId);
        shaShippingAddress = shaShippingAddressMapper.selectByExample(shaShippingAddressExample).get(0);
        redisCache.setCacheObject("addressByStoId:" + shaShippingAddress.getStoId(), shaShippingAddress);
        return shaShippingAddress;
    }

    //支付一个原始订单,缓存stoById:
    @Override
    public boolean pay(int bvoId, GetAPayMessage getAPayMessage) {
        //查密码
        WaaWalletAccount waaWalletAccount = walletService.selectWalletById(bvoId);
        if (!waaWalletAccount.getPassword().equals(getAPayMessage.getPassword())) {
            throw new RuntimeException("Password so wrong!");
        }
        //新算钱,现价买入
        StoStoreOrder stoStoreOrder = selectStoById(getAPayMessage.getStoId());
        ProProduct proProduct = proProductMapper.selectByPrimaryKey(stoStoreOrder.getProid());
        //运费加商品总钱
        BigDecimal total = proProduct.getRetailPrice().multiply(new BigDecimal(String.valueOf(stoStoreOrder.getQty()))).add(new BigDecimal(getAPayMessage.freightCost));
        //bvo,mvo,total,让钱包自己去做钱包的事吧
        walletService.pay(bvoId, manManufacturerMapper.selectByPrimaryKey(proProduct.getManId()).getSysUserId(), total);
        //原始订单修改
        stoStoreOrder.setPurchasePrice(proProduct.getRetailPrice());
        stoStoreOrder.setOrderStatus(2);
        stoStoreOrder.setLastUpdateBy(String.valueOf(bvoId));
        stoStoreOrderMapper.updateByPrimaryKey(stoStoreOrder);
        stoStoreOrder = stoStoreOrderMapper.selectByPrimaryKey(stoStoreOrder.getStoId());
        redisCache.setCacheObject("stoById:" + stoStoreOrder.getStoId(), stoStoreOrder);
        //新增销售订单
        SaoSalesOrder saoSalesOrder = new SaoSalesOrder();
        saoSalesOrder.setManId(proProduct.getManId());
        saoSalesOrder.setProid(stoStoreOrder.getProid());
        saoSalesOrder.setQty(stoStoreOrder.getQty());
        saoSalesOrder.setPrice(total);
        saoSalesOrder.setStoId(getAPayMessage.getStoId());
        saoSalesOrder.setPurchasePrice(proProduct.getRetailPrice());
        saoSalesOrder.setTrackingNo(UUID.randomUUID().toString().replace("-", ""));
        saoSalesOrder.setCreatedBy(String.valueOf(bvoId));
        saoSalesOrder.setUpdateBy(String.valueOf(bvoId));
        saoSalesOrder.setCallCnt(1);
        saoSalesOrder.setStsCd("1");
        saoSalesOrderMapper.insertSelective(saoSalesOrder);
        return true;
    }

    //根据stoId查询Sao，pro，str信息
    @Override
    public SendComplexSao selectSaoStrProByStoId(int stoId) {
        SaoSalesOrderExample saoSalesOrderExample = new SaoSalesOrderExample();
        SaoSalesOrderExample.Criteria saoSalesOrderExampleCriteria = saoSalesOrderExample.createCriteria();
        saoSalesOrderExampleCriteria.andStoIdEqualTo(stoId);
        List<SaoSalesOrder> saoSalesOrders = saoSalesOrderMapper.selectByExample(saoSalesOrderExample);
        if (saoSalesOrders.isEmpty()) {
            return null;
        }
        SaoSalesOrder saoSalesOrder = saoSalesOrders.get(0);
        ProProduct proProduct = proProductMapper.selectByPrimaryKey(saoSalesOrder.getProid());
        StrStore strStore = strStoreMapper.selectByPrimaryKey(stoStoreOrderMapper.selectByPrimaryKey(saoSalesOrder.getStoId()).getStoreId());
        return new SendComplexSao(saoSalesOrder, proProduct, strStore);
    }

    //根据时间和id模糊查询saostrpro
    @Override
    public List<SendComplexSao> selectSaoStrProByTime(GetASaoQueryByTime getASaoQueryByTime) {
        return saoSalesOrderMapper.selectSaoStrProByMany(getASaoQueryByTime);
    }

    //就是更新下订单状态,缓存"stoById:"+stoId
    @Override
    public boolean updateStoStatus(GetStoStatusQuery getStoStatusQuery) {
        StoStoreOrder stoStoreOrder = new StoStoreOrder();
        stoStoreOrder.setStoId(getStoStatusQuery.getStoId());
        stoStoreOrder.setOrderStatus(getStoStatusQuery.getStatus());
        stoStoreOrder.setLastUpdateBy(String.valueOf(getStoStatusQuery.getMvoId()));
        stoStoreOrderMapper.updateByPrimaryKeySelective(stoStoreOrder);
        stoStoreOrder = stoStoreOrderMapper.selectByPrimaryKey(getStoStatusQuery.getStoId());
        redisCache.setCacheObject("stoById:" + stoStoreOrder.getStoId(), stoStoreOrder);
        return true;
    }
}
