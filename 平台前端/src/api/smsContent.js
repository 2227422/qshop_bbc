import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/smsContent',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/smsContent/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/smsContent',
    method: 'put',
    data
  })
}

export default { add, edit, del }
