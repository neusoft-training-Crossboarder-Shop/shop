import request from '@/utils/request'


//1.判断是否有账号  需要返回true 或者 false
export function getWalletAccount() {
  return request({
    url: '/wallet/account/',
    method: 'get',
  })
}

//2.注册账号  不用返回数据
export function addWalletAccount(data) {
  return request({
    url:'/wallet/account/',
    method:'post',
    data:data
  })
}

//3.通过密码登录 这一步不用返回数据 告诉我成功 true 还是失败了 false 就行
export function walletAccountLogin(data) {
  return request({
     url:'/wallet/account/login',
     method:'post',
     data:data
  })
}

//4.更新钱包账号  不要返回数据
export function updateWalletAccount(data) {
  return request({
    url:'/wallet/account/',
    method:'put',
    data:data
  })
}

//5.返回钱包余额表信息  需要返回钱余额数据
export function getWalletAccountFund(data) {
  return request({
    url:'/wallet/fund/',
    method:'get',
  })
}



//6.分页返回钱包流水表数据  需要返回交易流水记录
export function getWalletTransaction(param) {
  return request({
    url:'/wallet/transaction/',
    method:'get',
    params:param
  })
}

