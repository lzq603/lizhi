// pages/update_userInfo/stuid/stuid.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    myCard:{},
    appInstance:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let app = getApp()
    this.setData({appInstance:app})
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

  bindsid: function(data){
    wx.showLoading({
      title: '正在验证'
    })
    let that = this;
    console.log(data.detail.value)
    var Url = this.data.appInstance.globalData.host + "Identify?userid=" + this.data.appInstance.globalData.userInfo.userId
    console.log(Url)
    wx.request({
      url: Url,
      dataType: 'json',
      data:data.detail.value,
      method:'get',
      success(res){
        console.log(res.data)
        switch(res.data.result){
          case 'right':
          wx.hideLoading()
          wx.showToast({
            title: '绑定成功',
            icon: 'success',
            success: function(){
              that.data.appInstance.globalData.userInfo.userName = data.detail.value.username
              setTimeout(function () {
                wx.navigateBack({})
              }, 1500)
            }
          })
            break;
          case 'error':wx.showToast({
            title: res.data.str,
            image: "/image/close.png"
          });break;
        }
      }
    })
  },
  fillTest: function(){
    this.setData({'myCard.stuid':'20163594'})
    this.setData({'myCard.password':'103960'})
  }
})