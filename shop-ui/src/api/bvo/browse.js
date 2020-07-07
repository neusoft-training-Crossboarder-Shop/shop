import request from '@/utils/request'


export function search(query){
  return request({
    url: '/bvo/good/browse',
    method: 'get',
    params:query
  })
}

export function getGoodDetail(id){
  return request({
    url : 'bvo/good/'+id,
    method : 'get',
  })
}
