import request from '@/utils/request'

export function listCategory(query) {
  return request({
    url: '/mvo/category/list',
    method: 'get',
    params: query
  })
}

export function createCategory(data) {
  return request({
    url: '/mvo/category/create',
    method: 'post',
    data
  })
}

export function readCategory(data) {
  return request({
    url: '/mvo/category/read',
    method: 'get',
    data
  })
}

export function updateCategory(data) {
  return request({
    url: '/mvo/category/update',
    method: 'post',
    data
  })
}

export function deleteCategory(data) {
  return request({
    url: '/mvo/category/delete',
    method: 'post',
    data
  })
}
