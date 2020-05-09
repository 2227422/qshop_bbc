import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/orderRefund',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/orderRefund/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/orderRefund',
    method: 'put',
    data
  })
}

export function refund(data) {
  console.log(2222)
  return request({
    url: 'api/orderRefund/refund',
    method: 'post',
    data
  })
}

export default { add, edit, del, refund }
