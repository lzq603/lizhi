// pages/update_userInfo/update_userInfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:{},
    appInstance:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    let app = getApp()
    this.setData({appInstance:app})
    this.setData({userInfo:app.globalData.userInfo})
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
    let appInstance = getApp()
    this.setData({ userInfo: appInstance.globalData.userInfo })
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

    //处理数据

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
  selectSex: function(){
    let that = this
    wx.showActionSheet({
      itemList: ['男','女'],
      success(res){
        if(res.tapIndex == 0)
          that.updateSex(true)
        else if(res.tapIndex == 1)
          that.updateSex(false)
      }
    })
  },

  updateSex: function (s) {
    let that = this
    var Url = this.data.appInstance.globalData.host + "UpdateUser?userId=" + this.data.appInstance.globalData.userInfo.userId
    wx.request({
      url: Url,
      data: {"gender":s},
      dataType: 'json',
      method: 'get',
      success(res) {
        if (res.statusCode == 200 && res.data.status == 'OK') {
          wx.showToast({
            title: '修改成功',
            icon: 'success',
            success(res) {
              that.data.appInstance.globalData.userInfo.gender = s
              that.setData({'userInfo.gender':s})
              /*setTimeout(function () {
                wx.navigateBack({})
              }, 1500)*/
            }
          })
        } else {
          wx.showToast({
            title: '修改失败',
            image: '/image/close.png'
          })
        }
      }
    })
  }
})