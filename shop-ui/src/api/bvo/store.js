import request from '@/utils/request'
// 查询商店列表
export function getStoreList(query) {
  return request({
    url: '/bvo/store/list',
    method: 'get',
    params: query
  })
}

export  function getStore(id){
  return request({
    url:'/bvo/store/'+id,
    method: 'get'
  })
}

export  function updateStore(store){
   return request({
     url:'/bvo/store',
     method:'put',
     data:store
   })
}

export function addStore(store){
  return request({
    url:'/bvo/store',
    method:'post',
    data:store
  })
}

export function delStore(storeId) {
  return request({
     url:'/bvo/store/'+storeId,
     method:'delete',
  })
}
