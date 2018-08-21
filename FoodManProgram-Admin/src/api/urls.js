// 商家的所有url路径
export const storeUrlPath = {
  // 查询全部商家
  pageUrl: '/store/queryStoreAll',
  // 添加商家
  addUrl: '/store/addStore'
}

// 商品的所有url路径
export const productUrlPath = {
  // 查询全部商品
  pageUrl: '/product/queryProductAll'
}

// 查询商品请求参数
export const productParam = {
  'data': {
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
  },
  'datalist': [
    {
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
    }
  ],
  'page': 1,
  'rows': 10,
  'sidx': '',
  'sord': ''
}
