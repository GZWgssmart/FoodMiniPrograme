// 商品的所有url路径
export const urls = {
  // 查询全部商品
  pageUrl: '/product/queryProductAll',
  // 添加商品
  addUrl: '/product/addProduct',
  // 编辑商品
  updateUrl: '/product/updateProduct',
  // 启用禁用商品
  statusUrl: '/product/enableddisable'
}

// 查询商品请求参数
export const conParam = {
  'data': {
    'sid': '',
    'appid': '',
    'createtime': '',
    'des': '',
    'id': '',
    'img': '',
    'imgdetail': '',
    'name': '',
    'price': '',
    'status': '',
    'title': '',
    'pricestart': '',
    'priceend': ''
  },
  'datalist': [{
    'appid': 0,
    'createtime': '',
    'des': '',
    'id': 0,
    'img': '',
    'imgdetail': '',
    'name': '',
    'price': 0,
    'sid': 0,
    'status': '',
    'title': ''
  }],
  'page': 1,
  'rows': 10,
  'sidx': '',
  'sord': ''
}

// 添加和编辑用到的参数
export const formParam = {
  'appid': '',
  'createtime': '',
  'des': '',
  'id': '',
  'img': '',
  'imgdetail': '',
  'name': '',
  'price': '',
  'sid': '',
  'status': '启用',
  'title': ''
}
