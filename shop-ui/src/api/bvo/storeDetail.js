import request from '@/utils/request'


//StoreDetail

//1.
// queryParams:{
//     proId:       模糊匹配
//     strId:       商店ID 前端参数
//     pageNum:1,
//     pageSize:10
//     startTime:'',
//     endTime:'',
// },
export function listDropshipItem(params) {
  return request({
    url : 'store/storeDetail/list',
    method : 'get',
    params : params
  })
}
//2.

//dilId : 主键
//status: 1: 上架 2：下架
export function updateDropshipStatus(dilId,status){
    return request({
      url:'store/storeDetail/'+dilId+'/'+status,
      method: 'put',
    })
}
//更改上架价格 和 数量
// data:{
//    dilId:
//    salPrice:
//    shelfStockAmount
// }
//3.
export function updateDropshipPriceAmount(data) {
  return request({
    url:'store/storeDetail/',
    method: 'put',
    data:data
  })
}
