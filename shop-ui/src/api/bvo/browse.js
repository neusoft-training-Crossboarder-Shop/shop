import request from '@/utils/request'

export function clearCache() {
  return request({
    url: '/bvo/browse/clearCache',
    method: 'delete'
  })
}

export function search(data){
  return request({
    url: '/bvo/browse/search',
    method: 'get',
    data:data
  })
}

export function getGoodDetail(id){
  return request({
    url : 'bvo/good/'+id,
    method : 'get',
  })
}
