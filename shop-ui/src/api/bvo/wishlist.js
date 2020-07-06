import request from '@/utils/request'

export function getWishList() {
  return request({
    url:'/bvo/wishlist/list',
    method:'get',
  })
}

export function removeFromList(proId){
  return request({
    url:'/bvo/wishlist/'+proId,
    method: 'delete'
  })
}

export function addProIntoWishList(data) {
  return request({
    url:'/bvo/wishlist/',
    method:'post',
    data : data
  })
}
