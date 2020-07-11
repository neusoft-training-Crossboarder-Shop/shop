import request from '@/utils/request'


//返回 ： 分页数据
export function listAuditRecord(data){
  return request({
     url: '/system/audit/list',
     method:'get',
     params : data
  })
}

//什么返回值都不要 成功就Ajax.success(). 错误就Ajax.error(msg)
export function acceptAuditRecord(ids){
  return request({
    url:'/system/audit/accept/'+ids,
    method:'put',
  })
}


export function refuseAuditRecord(ids){
  return request({
    url:'/system/audit/refuse/'+ids,
    method:'put',
  })

}
