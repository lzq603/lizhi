// pages/good/good.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    good:{},
    goodImgs:[],
    appInstance:{},
    msgList:[],
    leave_msg:false,
    _options:{},
    tempMsg:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({_options:options})
    let that = this
    let app = getApp()
    this.setData({appInstance:app})
    
    console.log(options.goodId)
    let Url = app.globalData.host + 'Good?loadBy=id&id=' + options.goodId
    wx.request({
      url: Url,
      success(res){

        //加载商品信息
        console.log(res)
        let bean = res.data
        that.setData({good:bean})

        //加载商品图片
        var imgs = bean.images.split(";")
        imgs.splice(imgs.length - 1, 1)
        for (var i = 0; i < imgs.length; i++)
          imgs[i] = that.data.appInstance.globalData.host + "img/goods/" + imgs[i]
        that.setData({ goodImgs: imgs })

        //加载收藏信息
        Url = app.globalData.host + 'CollectGood'
        wx.request({
          url: Url,
          data: {
            action:'isCollected',
            userId: app.globalData.userInfo.userId,
            goodId:bean.goodId
          },
          dataType:'json',
          success(res){
            that.setData(res.data)
          }
        })

        //加载留言列表
        Url = app.globalData.host + "Messages?type=getLeaveMsg&goodid=" + that.data.good.goodId
        wx.request({
          url: Url,
          dataType: 'json',
          success(res) {
            console.log(res.data)
            that.setData({msgList:res.data})
          }
        })
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
    this.onLoad(this.data._options)
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

  leave_msg:function(){

    this.setData({ leave_msg: true })
  },

  cancel_msg:function(){

    this.setData({ leave_msg: false })
  },

  //用户点击发送按钮
  send:function(res){
    console.log(res.detail.value)
    var Url = this.data.appInstance.globalData.host + "Messages"
    var send = this.data.appInstance.globalData.userInfo.userId
    let that = this

    console.log(send)

    wx.request({
      url: Url,
      data:{
        'type':'leave_msg',
        'send':send,
        'receive':that.data.good.goodOwner.userid,
        'good':that.data.good.goodId,
        'content':res.detail.value.content
      },
      success(res){
        console.log(res.data)
        that.onLoad(that.data._options)
        that.setData({ tempMsg: '' })
      }
    })
  },

  chat(res){
    wx.navigateTo({
      url: '/pages/chat/chat?sendId=' + this.data.good.goodOwner.userid + '&sendUser=' + this.data.good.goodOwner.username + '&sendNick=' + this.data.good.goodOwner.nickname + '&goodId=' + this.data.good.goodId
    })
  },
  //收藏商品
  collect:function(e){
    let app = getApp()
    let that = this
    let Url = app.globalData.host + 'CollectGood'
    wx.request({
      url: Url,
      data: {
        action: 'collect',
        userId: app.globalData.userInfo.userId,
        goodId: this.data.good.goodId
      },
      dataType: 'json',
      success(res) {
        
        console.log(res.data)
        if (res.statusCode == 200) {
          if (res.data.status == 'OK') {
            that.setData({ isCollect: true })
            wx.showToast({
              title: '收藏成功'
            })
            that.setData({"good.collect":that.data.good.collect + 1})
          } else {
            wx.showToast({
              title: '收藏失败',
              image: '/image/close.png'
            })
          }
        }else {
          wx.showToast({
            title: '收藏失败',
            image: '/image/close.png'
          })
        }
      }
    })
  },
  //取消收藏商品
  unCollect: function (e) {
    let app = getApp()
    let that = this
    let Url = app.globalData.host + 'CollectGood'
    wx.request({
      url: Url,
      data: {
        action: 'unCollect',
        userId: app.globalData.userInfo.userId,
        goodId: this.data.good.goodId
      },
      dataType: 'json',
      success(res) {

        console.log(res.data)
        if (res.statusCode == 200) {
          if (res.data.status == 'OK') {
            that.setData({ isCollect: false })
            wx.showToast({
              title: '取消成功'
            })
            that.setData({ "good.collect": that.data.good.collect - 1})
          } else {
            wx.showToast({
              title: '取消失败',
              image: '/image/close.png'
            })
          }
        } else {
          wx.showToast({
            title: '取消失败',
            image: '/image/close.png'
          })
        }
      }
    })
  },
  viewImage:function(e){
    let that = this
    wx.previewImage({
      current:e.currentTarget.dataset.imgurl,
      urls: that.data.goodImgs
    })
  }
})