import request from '@/utils/request'


// queryParams:{
//   storeOrderId:'',
//     pageNum:1,
//     pageSize:10
//     startTime:'',
//     endTime:'',
// },


export function listOrders(params) {
  return request(
    {
      url:'bvo/order/list',
      get:'method',
      params:params
    }
  )
}
