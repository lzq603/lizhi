// pages/chat/chat.js
const utils = require('../../utils/util.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goodId:0,
    appInstance:{},
    sendUser:{},
    msgList:[],
    opts:{},
    tempMsg:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    this.setData({opts:options})
    let that = this
    let app = getApp()
    this.setData({appInstance:app})

    if(options){
      wx.setNavigationBarTitle({title:options.sendNick})

      console.log(options)

      //传入对方用户数据
      var user = new Object()
      user.userId = options.sendId
      user.userName = options.sendUser
      user.nickName = options.sendNick
      this.setData({sendUser:user})
      //传入商品Id
      this.setData({goodId:options.goodId})
    }

    //加载与当前用户的聊天记录
    var Url = app.globalData.host + "Messages?receiveid=" + app.globalData.userInfo.userId + "&sendid=" + this.data.sendUser.userId + "&goodid=" + this.data.goodId + "&type=msgList"
    console.log("聊天内容")
    console.log(Url)
    wx.request({
      url: Url,
      dataType:'json',
      success(res){
        let msgs = res.data
        console.log(msgs)
        that.setData({msgList:msgs})
        that.setTime()
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
    this.onLoad(this.data.opts)
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

  sendMsg:function(data){
    let app = this.data.appInstance
    let that = this
    console.log(data.detail)
    var msg = data.detail.value.msg
    var Url = this.data.appInstance.globalData.host + "Messages?type=add&message=" + msg + "&sendid=" + this.data.appInstance.globalData.userInfo.userId + "&receiveid=" + this.data.sendUser.userId + "&goodid=" + this.data.goodId
    wx.request({
      url: Url,
      dataType: 'json',
      success(res){
        console.log(res)
        //加载与当前用户的聊天记录
        var Url = app.globalData.host + "Messages?receiveid=" + app.globalData.userInfo.userId + "&sendid=" + that.data.sendUser.userId + "&goodid=" + that.data.goodId + "&type=msgList"
        wx.request({
          url: Url,
          dataType: 'json',
          success(res) {
            let msgs = res.data
            console.log(msgs)
            that.setData({ msgList: msgs })
            that.onLoad()
            that.setData({ tempMsg:'' })
          }
        })
      }
    })
  },
  setTime:function(){
    var msgList = this.data.msgList
    console.log(msgList)
    var timeList = []
    for(var i = 0;i < msgList.length;i++){

      timeList.push(utils.formatTime(new Date(msgList[i].time)).slice(0,-3))
    }
    console.log(timeList)
    this.setData({timeList:timeList})
  }
})