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
      get:'method',
      params:params
    }
  )
}

