import request from '@/utils/request'

export function listBrand(query) {
  return request({
    url: '/mvo/brand/list',
    method: 'get',
    params: query
  })
}

export function createBrand(data) {
  return request({
    url: '/mvo/brand/create',
    method: 'post',
    data
  })
}

export function readBrand(data) {
  return request({
    url: '/mvo/brand/read',
    method: 'get',
    data
  })
}

export function updateBrand(data) {
  return request({
    url: '/mvo/brand/update',
    method: 'post',
    data
  })
}

export function deleteBrand(data) {
  return request({
    url: '/mvo/brand/delete',
    method: 'post',
    data
  })
}
