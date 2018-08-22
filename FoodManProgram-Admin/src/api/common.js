import axios from '@/libs/api.request'

/**
* ajax请求
* @param url 请求的url
* @param params 请求参数
* @param type 请求类型
*/
export function ajaxFun (url, params, type) {
  return axios.request({
    url: url,
    data: params,
    method: 'post'
  })
}
