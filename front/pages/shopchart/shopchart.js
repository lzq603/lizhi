// pages/shopchart/shopchart.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this

    //获取用户信息
    let user = app.globalData.userInfo
    this.setData({ appInstance: app })

    var Url = app.globalData.host + "Good?loadBy=CollectUser&userId=" + user.userId
    wx.request({
      url: Url,
      dataType: 'json',
      success: function (res) {
        if (res.statusCode == 200) {
          that.setData({ goods: res.data })
        }
      },
      fail(res) {
        console.log(res)
      }
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
  showMenu: function (data) {
    let that = this
    let goodId = data.currentTarget.dataset.goodid
    console.log(goodId)
    wx.showActionSheet({
      itemList: ["删除", "分享"],
      success(res) {
        console.log(res.tapIndex)
        switch (res.tapIndex) {
          case 0: //删除
            wx.showModal({
              title: '删除商品',
              content: '确定删除该商品吗？',
              success(res) {
                if (res.confirm) {
                  var Url = app.globalData.host + "CollectGood?action=unCollect&goodId=" + goodId + "&userId=" + app.globalData.userInfo.userId
                  wx.request({
                    url: Url,
                    dataType: 'json',
                    success(res) {
                      console.log(res)
                      if (res.data.status == 'OK') {
                        wx.showToast({
                          title: '已删除'
                        })
                        that.onLoad();
                      } else {
                        wx.showToast({
                          title: '发生未知错误',
                          image: '/image/close.png'
                        })
                        that.onLoad()
                      }
                    },
                    fail(res) {
                      wx.showToast({
                        title: '网络错误',
                        image: '/image/close.png'
                      })
                    }
                  })
                }
              }
            })
            break;
          case 1: //分享
            break;
        }
      }
    })
  }
})