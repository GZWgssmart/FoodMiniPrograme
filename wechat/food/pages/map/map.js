const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    company: {
      name: '**美食',
      address: '赣州市',
      longitude: '',
      latitude: ''
    },
    markers: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var self = this
    // 访问后台，把返回的数据设置到 teas 变量
    wx.request({

      url: app.globalData.baseUrl + 'store/queryStroeProductByAppid',
      method: 'post',
      dataType: 'json',
      data: { 'appid': "app1" },
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
    this.setData({
      markers: [
        {
          id: 0,
          longitude: this.data.company.longitude,
          latitude: this.data.company.latitude,
          width: 50,
          height: 50
        }
      ]
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  markertap(e) {
    wx.openLocation({
      latitude: this.data.company.latitude,
      longitude: this.data.company.longitude,
      scale: 17,
      name: this.data.company.name,
      address: this.data.company.address
    })
  }
})