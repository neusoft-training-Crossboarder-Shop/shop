import request from '@/utils/request'

// 登录方法
export function login(username, password, code, uuid) {
  const data = {
    username,
    password,
    code,
    uuid
  }
  return request({
    url: '/login',
    method: 'post',
    data: data
  })
}

// 获取User 详细信息
export function getInfo() {
  return request({
    url: '/getInfo',
    method: 'get'
  })
}

export function addUser(data) {
  return request({
    url: '/register',
    method: 'post',
    data: data
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

// 获取Validation Code
export function getCodeImg() {
  return request({
    url: '/captchaImage',
    method: 'get'
  })
}
