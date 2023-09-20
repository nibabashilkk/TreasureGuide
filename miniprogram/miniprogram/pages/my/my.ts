// pages/my/my.ts
Page({

  /**
   * 页面的初始数据
   */
  data: {
      version:'',
      updateDateTime:"2023-08-19",
      favorites:Number(),
      historys:Number()
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    const acountInfo = wx.getAccountInfoSync();
    this.setData({
      version: acountInfo.miniProgram.version
    })
  },
  star(){
      wx.navigateTo({
          url:'../star/star'
      })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    wx.request({
        url: "http://127.0.0.1:8081/api/article/star",
        header:{
          "satoken": wx.getStorageSync('token')
        },
        success:(res:any)=>{
          let data = res.data.data;
          if(data != null){
              this.setData({
                favorites:data.length
              })
          } else {
            this.setData({
                favorites:0
              })
          }
        }
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },
  handleNav(e:any){
    let type = e.currentTarget.dataset.type;
    switch(type) {
        case("favorites"):
            wx.navigateTo({
                url:"../star/star"
            })
            break;
        case("historys"):
            wx.navigateTo({
                url:'../star/star'
            })
    }
  }
})