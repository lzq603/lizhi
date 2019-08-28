// pages/search_result/search_result.js
const app = getApp()
const pageSize = 10
Page({

  /**
   * 页面的初始数据
   */
  data: {
    key_word:'',
    goods:[],
    appInstance:{},
    unfoldCatagory:false,
    // catagory:'全部',
    condition:{}, //查询条件
    catagroyList: ['全部','图书音像','学习办公', '数码产品', '服饰', '运动户外', '居家日用','其它'],
    page:0,
    tip:'正在加载更多信息……'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    var app = getApp()
    this.setData({appInstance:app})
    this.resetPage()
    console.log(options)
    if(!options.key)
      options.key = ""
    this.setData({'condition.key':options.key})
    if(options.catagory){
      this.setData({'condition.catagory':options.catagory})
    }
    this.refresh()
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
    if (this.data.tip == '没有更多商品了')
      return
    let that = this
    this.setData({ page: this.data.page + pageSize })
    var app = this.data.appInstance
    var Url = app.globalData.host + 'Good?loadBy=condition' + '&start=' + this.data.page + '&end=' + pageSize
    console.log(Url)
    wx.request({
      url: Url,
      data:that.data.condition,
      success(res) {
        console.log("res的长度是" + res.data.length)
        if (res.data.length == 0)
          that.setData({ tip: '没有更多商品了' })
        else {
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

  search:function(res){

    var Url = '/pages/search_result/search_result?key=' + res.detail.value
    console.log(Url)
    wx.redirectTo({
      url: Url,
      success(res){
        console.log(res)
      }
    })
  },

  //展开/合上分类
  unfoldCatagory:function(){
    this.setData({ unfoldCatagory: !this.data.unfoldCatagory})
  },
  //修改分类
  setCatagory:function(e){
    this.resetPage()
    let that = this
    console.log(e)
    let cata = e.currentTarget.dataset.catagory
    this.setData({'condition.catagory':cata})
    let Url = app.globalData.host + 'Good?loadBy=condition' + '&start=0&end=10'
    if(cata == '全部')
      this.setData({'condition.catagory':''})
    wx.request({
      url: Url,
      data:that.data.condition,
      success(res) {
        console.log(res.data)
        that.setData({ goods: res.data })
      }
    })
    this.setData({unfoldCatagory:false})
  },

  //筛选
  filter:function(){
    wx.showModal({
      title: '提示',
      content: '此功能暂未开放，敬请期待',
      showCancel:false
    })
  },
  //根据当前条件重新加载商品
  refresh:function(){
    let that = this
    let Url = app.globalData.host + 'Good?loadBy=condition' + '&start=0&end=10'
    wx.request({
      url: Url,
      data: this.data.condition,
      success(res) {
        console.log(res.data)
        that.setData({ goods: res.data })
      }
    })
  },

  //重置分页
  resetPage:function(){
    this.setData({ page: 0 })
    this.setData({ tip: '正在加载更多信息……' })
  }
})