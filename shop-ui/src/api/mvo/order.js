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
      url:'mvo/order/salOrder/list',
      method:'get',
      params:params
    }
  )
}

export function updateOrderStatus(data) {
  return request({
    url: 'mvo/order/stoOrder/status',
    method: 'put',
    data : data
  })
  
}

