//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    company: {},
    products:[],
    title:''
  },



  onShow: function() {
    var self = this
    // 访问后台，把返回的数据设置到 teas 变量
    wx.request({
      url: app.globalData.baseUrl + 'store/queryStroeProductByAppid',
      method: 'post',
      dataType: 'json',
      data: {'appid':"app1"},
      success: function (res) {
        console.log(res.data.data.products);
        console.log(res.data.data.store.name);
        self.setData({
          company: res.data.data.store,
          products: res.data.data.products
        })

        wx.setNavigationBarTitle({
          title: res.data.data.store.name
        })
        
      }
    })

  },
  showDetail: function(event) {
    var current = event.currentTarget.dataset.products
    wx.navigateTo({
      url: '../detail/detail?id=' + current.id + '&img=' + current.img + '&title=' + current.title + '&des=' + current.des + '&price=' + current.price + '&salePrice=' + current.imgdetail,
      success: function(res) {},
      fail: function(res) {},
      complete: function(res) {},
    })
  },
 
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: 'http:'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }

})
