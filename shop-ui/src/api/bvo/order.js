import request from '@/utils/request'


// queryParams:{
//   storeOrderId:'',
//     pageNum:1,
//     pageSize:10
//     startTime:'',
//     endTime:'',
// },
//
//生成订单
// let data = {
//   amount:this.amount,  数量
//   productId:this.pro.proId,  产品id
//   storeIds:this.selectedStore, 商店列表 Array
// }
//
export function purchaseProduct(data) {
   return request({
     url:'bvo/browse/purchase',
     method:'post',
     data:data
   })
}
//返回Ajax.insertSuccess

// Confirm 收货
//1. Modify  原订单Status为Alreadycomplete 5
//2.在 sti_store_dropship_item 中生成一条记录


//原始订单ID
export function acceptProduct(stoId) {
  return request({
    url:'bvo/order/accept/'+stoId,
    method:'put',
  })
}

//返回Ajax.updateSuccess

export function listOrders(params) {
  return request(
    {
      url:'/bvo/order/stoOrder/list',
      get:'method',
      params:params
    }
  )
}


//查询单个的原始订单
export function getStoByStoId(stoId) {
  return request(
    {
      url:'bvo/order/stoOrder/'+stoId,
      method:'get',
    }
  )
}

//更新单个的原始订单
//data{
//     stoId,
//     qty,
// }
export function updateStoByStoId(data) {
  return request(
    {
      url:'bvo/order/stoOrder/qty',
      method:'put',
      data:data
    }
  )
}


export function getShippingAddressByStoId(stoId) {
  return request(
    {
      url:'bvo/order/shippingAddress/'+stoId,
      method:'get',
    }
  )
}

export function insertShippingAddress(shippingAddress) {
  return request(
    {
      url:'bvo/order/shippingAddress',
      method:'post',
      data:shippingAddress
    }
  )
}
//
// input
// data{
//   stoId:sotId,
//     password,
//     freightCost,
// }
// MVO BVO公用的
export function payStoBySto(data) {
  return request(
    {
      url:'bvo/order/stoOrder/pay',
      method:'post',
      data:data
    }
  )
}

export function updateShippingAddress(shippingAddress) {
  return request(
    {
      url:'bvo/order/shippingAddress',
      method:'put',
      data:shippingAddress
    }
  )
}
