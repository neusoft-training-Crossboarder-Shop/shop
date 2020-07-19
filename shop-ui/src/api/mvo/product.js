import request from '@/utils/request'

export  function api_getProductList(params) {
   return request({
     url:'/mvo/product/list',
     method:'get',
     params:params
   })
}

export function api_getProductByProId(id){
  return request({
    url:'/mvo/product/'+id,
    method:'get',
  })
}

export function api_insertProduct(data) {
  return request({
    url:'/mvo/product/',
    method:'post',
    data:data
  })
}

export function api_updateProduct(data){
  return request({
    url:'/mvo/product/',
    method:'put',
    data:data
  })
}

export function api_deleteProduct(ids) {
  return request({
    url:'/mvo/product/'+ids,
    method:'delete',
  })
}

export function api_getAllBrands() {
  return request({
    url:'/mvo/brand/all',
    method:'get',
  })
}

export function api_uploadProductImage(data) {
  return request({
      url:'/mvo/product/image',
      method:'post',
      data:data
  })
}

export function api_deleteProductImageByImageId(imgId) {
  return request({
     url:'/mvo/product/image/'+imgId,
     method:'delete'
  })

}


export function api_getProductDescriptionByProId(proId) {
  return request({
    url:'/mvo/product/description/'+proId,
    method:'get',
  })
}



export function api_updateProductDescription(descriptions) {
  return request({
    url:'/mvo/product/description/',
    method:'put',
    data: descriptions
  })
}

export function api_getProductImageByProId(proId) {
  return request({
    url: '/mvo/product/image/' + proId,
    method:'get',
  })
}

export function api_updateProductProState(proId,status){
  return request({
    url:'mvo/product/'+proId+'/'+status,
    method: 'put',
  })
}
