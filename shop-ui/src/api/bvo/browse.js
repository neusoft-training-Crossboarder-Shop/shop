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
