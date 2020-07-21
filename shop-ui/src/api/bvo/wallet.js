import request from '@/utils/request'


//1.判断是否有Account   需要返回true 或者 false
export function getWalletAccount() {
  return request({
    url: '/wallet/account/',
    method: 'get',
  })
}


//2.Register Account   不用返回Data
export function addWalletAccount(data) {
  return request({
    url:'/wallet/account/',
    method:'post',
    data:data
  })
}

//3.通过密码登录 这一步不用返回Data  告诉我Success true 还是Fail了 false 就行
export function walletAccountLogin(data) {
  return request({
     url:'/wallet/account/login',
     method:'post',
     data:data
  })
}

//4.更新钱包Account   不要返回Data
export function updateWalletAccount(data) {
  return request({
    url:'/wallet/account/',
    method:'put',
    data:data
  })
}

//5.返回钱包余额表信息  需要返回钱余额Data 
export function getWalletAccountFund() {
  return request({
    url:'/wallet/fund/',
    method:'get',
  })
}



//6.分页返回钱包流水表Data   需要返回交易流水记录
export function getWalletTransaction(param) {
  return request({
    url:'/wallet/transaction/',
    method:'get',
    params:param
  })
}



//
//data:{
//  bankCardId:'',
//  operateMoney:'',
//
// }
//
export function withdrawAccount(data) {
  return request({
    url:'/wallet/transaction/withdraw',
    method:'post',
    data:data
  })
}

export function depositAccount(data) {
  return request({
    url:'/wallet/transaction/deposit',
    method:'post',
    data:data
  })
}

