import request from '@/utils/request'


// queryParams:{
//   storeOrderId:'',
//     pageNum:1,
//     pageSize:10
//     startTime:'',
//     endTime:'',
// },
//

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
