// pages/me/me.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {}
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
    let that = this

    var appInstance = getApp()
    this.setData({ userInfo: appInstance.globalData.userInfo })
    let user = appInstance.globalData.userInfo

    console.log(user)
    var Url = appInstance.globalData.host + "Statistic?userId=" + user.userId
    wx.request({
      url: Url,
      dataType: 'json',
      success(res){
        console.log(res.data)
        that.setData({statistic:res.data})
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
    this.onShow()
    wx.stopPullDownRefresh()
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

  about: function() {
    wx.showModal({
      title: '关于',
      content: '荔枝市场 V1.0'
    })
  }
})