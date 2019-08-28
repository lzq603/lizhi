// pages/msg/msg.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    msgList:[],
    appInstance:{}
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
    let app = getApp()
    this.setData({ appInstance: app })
    var Url = app.globalData.host + "Messages?receiveid=" + app.globalData.userInfo.userId + "&status=2"
    console.log(Url)
    wx.request({
      url: Url,
      dataType: 'json',
      success(res) {
        that.setData({ msgList: res.data })
        console.log(res.data)
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
    this.onLoad()
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

  showMenu: function(){
    wx.showActionSheet({
      itemList: ['删除'],
      success(res){
        switch(res.tapIndex){
          case 0:
            wx.showToast({
              title: '删除成功',
              icon: 'sucess'
            })
            break;
          default:
            wx.showToast({
              title: '操作失败',
              image: '/image/close.png'
            })
        }
      }
    })
  },
  onPullDownRefresh:function(){
    this.onShow()
    wx.stopPullDownRefresh()
  }
})