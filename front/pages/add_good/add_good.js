// pages/add_good/add_good.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goodImgs:[],
    catagoryList: ['请选择','图书音像', '学习办公', '数码产品', '服饰', '运动户外', '居家日用','其它'],
    index:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
    this.appInstance = getApp()
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

  //上传商品图片

  uploadImgs:function(data){

    var n = data.imgs.length
    var id = data.goodid
    var succeed = 0

    for(var i = 0;i < n;i++){
      wx.uploadFile({
        url: data.url,
        filePath: data.imgs[i],
        name: 'goodimg',
        formData:{
          "goodid":id
        },
        success:function(res){
          var result = JSON.parse(res.data)
          console.log(result)
          if(result.status == "OK"){
            succeed++
            data.log(succeed)
          }
        }
      })
    }
  },

  //确定发布
  addGood: function (e) {
    //空参检查
    if(this.data.index == 0)
    {
      wx.showModal({
        title: '提示',
        content: '请选择商品分类',
        showCancel: false
      })
      return 
    }

    let app = getApp()
    let that = this
    var Url = app.globalData.host + 'AddGood'
    var parameters = e.detail.value
    parameters.ownerId = app.globalData.userInfo.userId
    parameters.num = 1
    parameters.catagory = this.data.catagoryList[this.data.index]

    //空参检查
    if(parameters.name == ''){
      wx.showModal({
        title: '提示',
        content: '请输入商品名',
        showCancel: false
      })
      return
    }
    if (parameters.detail == '') {
      wx.showModal({
        title: '提示',
        content: '请输入商品描述',
        showCancel: false
      })
      return
    }
    if (parameters.price == '') {
      wx.showModal({
        title: '提示',
        content: '请输入商品价格',
        showCancel: false
      })
      return
    }
    if (parameters.foreprice == '') {
      wx.showModal({
        title: '提示',
        content: '请输入商品原价',
        showCancel: false
      })
      return
    }
    if (!this.data.goodImgs || this.data.goodImgs.length == 0)
    {
      wx.showModal({
        title: '提示',
        content: '请选择商品图片',
      })
      return
    }


    wx.request({
      url: Url,
      data:parameters,
      dataType:'json',
      success(res){

        console.log(res)

        if(res.statusCode == 200){
          if(res.data.status == 'OK'){
            
            console.log("提交成功")

            //上传图片
            that.uploadImgs({
              url: Url,
              imgs: that.data.goodImgs,
              goodid:res.data.goodid.toString(),

              //s为成功上传图片数
              log:function(s){
                //全部成功上传
                if(s == this.imgs.length){
                  wx.showToast({
                    title: '发布成功',
                    icon: 'success',
                    duration:5000,
                    success:function(){
                      setTimeout(function(){
                        wx.navigateBack({})
                      },1500)
                    }
                  })
                  
                }
              }
            })

          }
        }
      }
    })
    

    
  },

  //选择图片
  chooseImg:function(e){

    let that = this
    wx.chooseImage({
      count:7,
      success: function(res) {

        console.log("成功")
        if(res.errMsg == "chooseImage:ok"){
          console.log(res.tempFilePaths)
          that.setData({goodImgs:res.tempFilePaths})
        }
      },
      fail:function(res){

        console.log(res)
      }
    })
  },
  //设定分类
  changeCatagory:function(e){
    console.log(e)
    this.setData({index:e.detail.value})
  }
})