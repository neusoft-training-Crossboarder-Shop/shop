import request from '@/utils/request'

export function listCompany(query) {
  return request({
    url: '/company/list',
    method: 'get',
    params: query
  })
}

export function createCompany(data) {
  return request({
    url: '/company/create',
    method: 'post',
    data
  })
}

export function readCompany(data) {
  return request({
    url: '/company/read',
    method: 'get',
    data
  })
}

export function updateCompany(data) {
  return request({
    url: '/company/update',
    method: 'post',
    data
  })
}

export function deleteCompany(data) {
  return request({
    url: '/company/delete',
    method: 'post',
    data
  })
}
