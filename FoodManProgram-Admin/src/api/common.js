import axios from '@/libs/axios'


/**
* AJAX请求方法
* @param url 请求的url
* @param params 请求参数
* @param type 请求类型 post|get 默认post
*/
export function ajaxFun(url,params,type){
  if(type == null) {
    type = 'post';
  }
  if (type == "get") {
    return axios.request({
      url: url,
      method: 'get'
    })
  } else {
    return axios.request({
      url: url,
      params,
      method: 'post'
    })
  }
}
