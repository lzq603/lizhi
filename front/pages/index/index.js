// pages/index/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //轮播图
    homeImages:[
      "/image/zhihuan.jpg",
      "/image/banner2.jpg",
    ],
    goods:[],
    goodstr:[],
    appInstance:{},
    page:0,
    tip:'正在加载更多……'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({ tip:'正在加载更多……'})
    this.setData({page:0})
    this.setData({appInstance:getApp()})
    let that = this
    var app = this.data.appInstance
    this.setData({ tip: '正在加载更多……' })
    this.setData({ page: 0 })
    wx.request({
      url: app.globalData.host + 'Good?loadBy=condition&start=' + this.data.page + '&end=11',
      success(res) {
        console.log(res.data)
        that.setData({ goods: res.data })
        var goodstr = []
        for (var i = 0; i < that.data.goods.length; i++) {
          goodstr.push(JSON.stringify(that.data.goods[i]))
          that.setData({ goodstr: goodstr })
        }
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
    //this.data.appInstance.onLaunch()
    this.onLoad()
    wx.stopPullDownRefresh()
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    if (this.data.tip == '没有更多商品了')
      return
    let that = this
    this.setData({page:this.data.page + 1})
    var app = this.data.appInstance
    let Url = app.globalData.host + 'Good?loadBy=condition&start=' + (this.data.page * 10 + 1) + '&end=10'
    console.log(Url)
    wx.request({
      url: Url,
      success(res) {
        console.log("res的长度是" + res.data.length)
        if(res.data.length == 0)
          that.setData({tip:'没有更多商品了'})
        else{
          let goods = that.data.goods.concat(res.data)
          that.setData({ goods: goods })
        }
        // var goodstr = []
        // for (var i = 0; i < that.data.goods.length; i++) {
        //   goodstr.push(JSON.stringify(that.data.goods[i]))
        //   that.setData({ goodstr: goodstr })
        // }
      }
    })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  /*
   * 搜索商品
   */
  search:function(e){
    console.log("搜索：")
    var Url = '/pages/search_result/search_result?key=' + e.detail.value
    console.log(Url)
    wx.navigateTo({
      url: Url,
      success: function(res) {},
      fail: function(res) {},
      complete: function(res) {},
    })    
  },
  
  release:function(){
    if(this.data.appInstance.globalData.userInfo.userName){
      wx.navigateTo({
        url: '/pages/add_good/add_good',
      })
    }else{
      wx.showModal({
        title: '提示',
        content: '您还未进行学生认证，请先认证',
        success(res){
          if(res.confirm){
            wx.navigateTo({
              url: '/pages/update_userInfo/stuid/stuid'
            })
          }
        }
      })
    }
  }
})