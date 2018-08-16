//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    company: {}
  },

  onShow: function() {
    this.setData({
      company: {
        headicon: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534411241219&di=2082082d511e397ce8ec78c7b6c21cb6&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F61%2F89%2F07k58PICKzr_1024.jpg',
        video: 'http://wxsnsdy.tc.qq.com/105/20210/snsdyvideodownload?filekey=30280201010421301f0201690402534804102ca905ce620b1241b726bc41dcff44e00204012882540400&bizid=1023&hy=SH&fileparam=302c020101042530230204136ffd93020457e3c4ff02024ef202031e8d7f02030f42400204045a320a0201000400',
        products: [
          {
            id: 1,
            title: '菜品1',
            headicon: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534411241219&di=2082082d511e397ce8ec78c7b6c21cb6&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F61%2F89%2F07k58PICKzr_1024.jpg',
            price: 2000,
            des: '非常好！'
          },
          {
            id: 2,
            title: '菜品2',
            headicon: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534411241219&di=2082082d511e397ce8ec78c7b6c21cb6&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F61%2F89%2F07k58PICKzr_1024.jpg',
            price: 2000,
            des: '非常好！'
          }
        ]
      }
    })
  },
  showDetail: function(event) {
    let id = event.currentTarget.dataset.productid
    wx.navigateTo({
      url: '../detail/detail?id=' + id,
      success: function(res) {},
      fail: function(res) {},
      complete: function(res) {},
    })
  }
  /** 
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
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
  */
})
