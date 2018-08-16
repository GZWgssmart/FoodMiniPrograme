// pages/map/map.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    company: {
      name: '**美食',
      address: '赣州市',
      longitude: 114.928507,
      latitude: 25.819923
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