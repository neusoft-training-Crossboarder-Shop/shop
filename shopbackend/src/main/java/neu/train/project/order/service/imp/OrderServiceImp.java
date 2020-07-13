package neu.train.project.order.service.imp;


import neu.train.framework.redis.RedisCache;
import neu.train.project.order.mapper.ProProductMapper;
import neu.train.project.order.mapper.ShaShippingAddressMapper;
import neu.train.project.order.mapper.StoStoreOrderMapper;
import neu.train.project.order.mapper.StrStoreMapper;
import neu.train.project.order.pojo.*;
import neu.train.project.order.service.OrderService;
import neu.train.project.order.vo.GetAStoOrderQueryByTime;
import neu.train.project.order.vo.GetUpdateASto;
import neu.train.project.order.vo.SendASimpleSto;
import neu.train.project.order.vo.SendAStoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
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

    //根据起始终止时间查询StoStrPro
    @Override
    public List<SendASimpleSto> selectStoStrProByTime(GetAStoOrderQueryByTime getAStoOrderQueryByTime) {
        return stoStoreOrderMapper.selectStoStrProByMany(getAStoOrderQueryByTime);
    }

    //根据stoId查询单个StoStrPro信息，缓存"stoById:"+stoId
    @Override
    public  SendAStoOrder selectStoStrProById(int stoId){
        StoStoreOrder stoStoreOrder=selectStoById(stoId);
        StrStore strStore=strStoreMapper.selectByPrimaryKey(stoStoreOrder.getStoreId());
        ProProduct proProduct=proProductMapper.selectByPrimaryKey(stoStoreOrder.getProid());
        return new SendAStoOrder(stoStoreOrder,strStore,proProduct);
    }


    //根据stoId查询单个Sto信息，缓存"stoById:"+stoId
    @Override
    public StoStoreOrder selectStoById(int stoId){
       StoStoreOrder stoStoreOrder=redisCache.getCacheObject("stoById:"+stoId);
       if(stoStoreOrder!=null){
           return stoStoreOrder;
       }
       stoStoreOrder=stoStoreOrderMapper.selectByPrimaryKey(stoId);
       redisCache.setCacheObject("stoById:"+stoId,stoStoreOrder);
       return stoStoreOrder;
    }

    //根据stoId更新单个sto信息,其实就是更新个商品数量而已，缓存"stoById:"+stoId
    @Override
    public boolean updateStoById(String updateBy,GetUpdateASto getUpdateASto){
        StoStoreOrder stoStoreOrder=selectStoById(getUpdateASto.getStoId());
        stoStoreOrder.setUpdateBy(updateBy);
        stoStoreOrder.setQty(getUpdateASto.getQty());
        if(stoStoreOrderMapper.updateByPrimaryKeySelective(stoStoreOrder)==0){
            return false;
        }else{
            stoStoreOrder=stoStoreOrderMapper.selectByPrimaryKey(stoStoreOrder.getStoId());
            redisCache.setCacheObject("stoById:"+stoStoreOrder.getStoId(),stoStoreOrder);
            return true;
        }
    }

    //插入一条收获地址，缓存"addressByStoId:"+stoId
    @Override
    public boolean insertOneAddress(ShaShippingAddress shaShippingAddress){
        shaShippingAddressMapper.insertSelective(shaShippingAddress);
        redisCache.setCacheObject("addressByStoId:"+shaShippingAddress.getStoId(),shaShippingAddress);
        return true;
    }

    //更新一条收获地址，缓存"addressById:"+stoId
    @Override
    public boolean updateOneAddress(ShaShippingAddress shaShippingAddress){
        ShaShippingAddressExample shaShippingAddressExample=new ShaShippingAddressExample();
        ShaShippingAddressExample.Criteria shaShippingAddressExampleCriteria=shaShippingAddressExample.createCriteria();
        shaShippingAddressExampleCriteria.andStoIdEqualTo(shaShippingAddress.getStoId());
        shaShippingAddressMapper.updateByExampleSelective(shaShippingAddress,shaShippingAddressExample);
        shaShippingAddress=shaShippingAddressMapper.selectByExample(shaShippingAddressExample).get(0);
        redisCache.setCacheObject("addressByStoId:"+shaShippingAddress.getStoId(),shaShippingAddress);
        return true;
    }

    //查询一条收获地址，缓存"addressById:"+stoId
    @Override
    public ShaShippingAddress selectAddressByStoId(int stoId){
        ShaShippingAddress shaShippingAddress=redisCache.getCacheObject("addressByStoId:"+stoId);
        if(shaShippingAddress!=null){
            return shaShippingAddress;
        }
        ShaShippingAddressExample shaShippingAddressExample=new ShaShippingAddressExample();
        ShaShippingAddressExample.Criteria shaShippingAddressExampleCriteria=shaShippingAddressExample.createCriteria();
        shaShippingAddressExampleCriteria.andStoIdEqualTo(stoId);
        shaShippingAddress=shaShippingAddressMapper.selectByExample(shaShippingAddressExample).get(0);
        redisCache.setCacheObject("addressByStoId:"+shaShippingAddress.getStoId(),shaShippingAddress);
        return shaShippingAddress;
    }



}
