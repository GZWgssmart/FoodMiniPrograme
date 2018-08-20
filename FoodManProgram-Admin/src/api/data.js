import axios from '@/libs/api.request'

export const getTableData = () => {
  return axios.request({
    url: 'get_table_data',
    method: 'get'
  })
}

/**
* 获取店铺表格数据
*/
export const getStoreList = () => {
  return axios.request({
    url: '/store/queryAppAll',
    method: 'post'
  })
}
