// 小程序的所有url路径
export const urls = {
  // 查询全部小程序
  pageUrl: '/app/queryAppAll',
  // 添加小程序
  addUrl: '/app/addApp',
  // 编辑小程序
  updateUrl: '/app/updateApp',
  // 小程序下拉框
  selectUrl: '/app/queryAppValueLabel'
}
// 查询商品请求参数
export const conParam = {
  'data': {
    'createtime': '',
    'des': '',
    'id': '',
    'name': ''
  },
  'datalist': [{
    'createtime': '',
    'des': '',
    'id': '',
    'name': ''
  }],
  'page': 1,
  'rows': 10,
  'sidx': '',
  'sord': ''
}

// 添加和编辑用到的参数
export const formParam = {
  'createtime': '',
  'des': '',
  'id': '',
  'name': ''
}
