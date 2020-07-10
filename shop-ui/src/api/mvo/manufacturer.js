import request from '@/utils/request'

export function listManufacturer(query) {
  return request({
    url: '/mvo/manufacturer/list',
    method: 'get',
    params: query
  })
}

export function createManufacturer(data) {
  return request({
    url: '/mvo/manufacturer/create',
    method: 'post',
    data
  })
}

export function readManufacturer(data) {
  return request({
    url: '/mvo/manufacturer/read',
    method: 'get',
    data
  })
}

export function updateManufacturer(data) {
  return request({
    url: '/mvo/manufacturer/update',
    method: 'post',
    data
  })
}

export function deleteManufacturer(data) {
  return request({
    url: '/mvo/manufacturer/delete',
    method: 'post',
    data
  })
}
