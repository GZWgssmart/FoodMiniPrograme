// 商铺的所有url路径
export const urls = {
  // 查询全部商铺
  pageUrl: '/store/queryStoreAll',
  // 添加商铺
  addUrl: '/store/addStore',
  // 编辑商铺
  updateUrl: '/store/updateStore',
  // 启用禁用商铺
  statusUrl: '/store/enableddisable',
  // 根据appid查询商铺和商品
  allByAppIdUrl: '/store/queryStroeProductByAppid',
  // 根据appid查询所有店铺
  selectByAppIdUrl: '/store/queryStoreValueLabelByAppId'
}

// 查询商品请求参数
export const conParam = {
  'data': {
    'address': '',
    'appid': '',
    'appname': '',
    'createtime': '',
    'des': '',
    'id': '',
    'latitude': '',
    'logo': '',
    'longitude': '',
    'name': '',
    'status': '',
    'tel': '',
    'type': '',
    'video': ''
  },
  'datalist': [
    {
      'address': '',
      'appid': '',
      'appname': '',
      'createtime': '',
      'des': '',
      'id': '',
      'latitude': '',
      'logo': '',
      'longitude': '',
      'name': '',
      'status': '',
      'tel': '',
      'type': '',
      'video': ''
    }
  ],
  'page': 1,
  'rows': 10,
  'sidx': '',
  'sord': ''
}

// 添加和编辑用到的参数
export const formParam = {
  'address': '',
  'appid': '',
  'appname': '',
  'createtime': '',
  'des': '',
  'id': '',
  'latitude': 0,
  'logo': '',
  'longitude': 0,
  'name': '',
  'status': '启用',
  'tel': '',
  'type': '',
  'video': ''
}
