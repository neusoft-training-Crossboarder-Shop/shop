import request from '@/utils/request'

export function api_getManufacturer(data) {
    return request({
      url:'mvo/manufacturer/',
      method:'get',
    })
}

export function api_insertManufacturer(data){
  return request({
    url:'mvo/manufacturer/',
    method:'post',
    data:data
  })
}


export function api_updateManufacturer(data){
  return request({
    url:'mvo/manufacturer/',
    method:'put',
    data:data
  })
}


