import request from '@/utils/request'

export function getBasicInfo() {
  return request({
    url: '/bvo/profile',
    method: 'get',
  })
}

export function addDropShipper(data){
  return request({
    url : '/bvo/profile/',
    method: 'post',
    data : data
  })
}

export function updateDropShipper(data){
  return request({
    url : "/bvo/profile",
    method :"put",
    data: data
  })
}

