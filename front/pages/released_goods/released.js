// pages/released_goods/released.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    appInstance:{},
    goods:[]
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
    //获取小程序实例
    let app = getApp()
    this.setData({ appInstance: app })

    //获取用户信息
    let user = app.globalData.userInfo
    console.log(user)

    var Url = app.globalData.host + "Good?loadBy=userId&userId=" + user.userId
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
  showMenu: function(data){
    let that = this
    let goodId = data.currentTarget.dataset.goodid
    console.log(goodId)
    wx.showActionSheet({
      itemList: ["编辑","删除"],
      success(res){
        console.log(res.tapIndex)
        switch(res.tapIndex){
          case 0: //编辑
            wx.navigateTo({
              url: '/pages/update_good/update_good?id=' + goodId,
            })
            break;
          case 1: //删除
            wx.showModal({
              title: '删除商品',
              content: '确定删除该商品吗？',
              success(res){
                if(res.confirm){
                  var Url = that.data.appInstance.globalData.host + "Good?loadBy=id&id=" + goodId + "&action=delete"
                  wx.request({
                    url: Url,
                    dataType: 'json',
                    success(res) {
                      console.log(res)
                      if (res.data.status == 'OK') {
                        wx.showToast({
                          title: '已删除'
                        })
                        that.onShow();
                      } else {
                        wx.showToast({
                          title: '发生未知错误',
                          image: '/image/close.png'
                        })
                        that.onShow();
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
          case 2: //分享
            break;
        }
      }
    })
  },
  onShareAppMessage: function (res) {

    let good = res.target.dataset.good
    console.log(good.images)
    wx.showShareMenu({
      withShareTicket: true
    })
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
    }
    return {
      title: '转赚商城',
      path: '/pages/good/good?goodId=' + good.goodId,
      imageUrl: app.globalData.host + "img/goods/" + good.images
    }
  }
})