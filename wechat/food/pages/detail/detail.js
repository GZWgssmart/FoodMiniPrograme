// pages/detail/detail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    product: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showShareMenu({
      withShareTicket: true,
      success: function(res) {},
      fail: function(res) {},
      complete: function(res) {},
    })
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
    this.setData({
      product: {
        id: 1,
        title: '菜品1',
        des: '非常好！',
        price: 2000,
        headicon: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534411241219&di=2082082d511e397ce8ec78c7b6c21cb6&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F61%2F89%2F07k58PICKzr_1024.jpg',
        image: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534411241219&di=2082082d511e397ce8ec78c7b6c21cb6&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F61%2F89%2F07k58PICKzr_1024.jpg'
      }
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
  
  }
})