import axios from '@/libs/api.request'
import * as url from './urls.js'

export const getTableData = () => {
  return axios.request({
    url: 'get_table_data',
    method: 'get'
  })
}

// 获取店铺表格数据
export const getStoreList = () => {
  return axios.request({
    url: url.storeUrlPath.pageUrl,
    method: 'post'
  })
}

// 获取商品表格数据
export const getProductList = () => {
  return axios.request({
    url: url.productUrlPath.pageUrl,
    data: url.productParam,
    method: 'post'
  })
}
