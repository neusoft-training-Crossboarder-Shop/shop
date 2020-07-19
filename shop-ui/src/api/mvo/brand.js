import request from '@/utils/request'
//
export function api_getBrandList(query) {
  return request({
    url: '/mvo/brand/list',
    method: 'get',
    params: query
  })
}

export  function api_getBrand(id){
  return request({
    url:'/mvo/brand/'+id,
    method: 'get'
  })
}

export  function api_updateBrand(brand){
  return request({
    url:'/mvo/brand',
    method:'put',
    data:brand
  })
}

export function api_addBrand(store){
  return request({
    url:'/mvo/brand',
    method:'post',
    data:store
  })
}

export function api_delBrand(storeId) {
  return request({
    url:'/mvo/brand/'+storeId,
    method:'delete',
  })
}



export function api_uploadBrand(data) {
  return request({
    url: '/mvo/brand/image',
    method: 'post',
    data: data
  })
}


