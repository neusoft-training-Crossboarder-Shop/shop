import request from '@/utils/request'

export function listBrand(query) {
  return request({
    url: '/oldbrand/list',
    method: 'get',
    params: query
  })
}

export function createBrand(data) {
  return request({
    url: '/oldbrand/create',
    method: 'post',
    data
  })
}

export function readBrand(data) {
  return request({
    url: '/oldbrand/read',
    method: 'get',
    data
  })
}

export function updateBrand(data) {
  return request({
    url: '/oldbrand/update',
    method: 'post',
    data
  })
}

export function deleteBrand(data) {
  return request({
    url: '/oldbrand/delete',
    method: 'post',
    data
  })
}
