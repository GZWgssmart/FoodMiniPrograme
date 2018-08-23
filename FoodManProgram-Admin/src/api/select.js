import axios from '@/libs/api.request'
/**
* 状态的下拉框数据
*/
export const statusList = [

  {
    value: '启用',
    label: '启用'
  },
  {
    value: '禁用',
    label: '禁用'
  }
]

// 商铺类型
export const storeType = [
  {
    value: '酒店',
    label: '酒店'
  },
  {
    value: '店铺',
    label: '店铺'
  }
]

/**
*获取所有小程序或门店
*/
export function getAllList (url, type) {
  return axios.request({
    url: url,
    data: type,
    method: 'post'
  })
};

/**
* 根据appid获取所有门店
*/
export function getStoreAllListByAppId (url, param) {
  return axios.request({
    url: url,
    data: param,
    method: 'post'
  })
};
