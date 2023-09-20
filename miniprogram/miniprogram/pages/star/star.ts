import toast from "../../components/toast/toast";

// pages/star/star.ts
Page({

  /**
   * 页面的初始数据
   */
  data: {
      data:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    wx.setNavigationBarTitle({
        title: "收藏 | 宝藏指北",
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
    let that = this;
    wx.request({
        url: "http://127.0.0.1:8081/api/article/star",
        header:{
          "satoken": wx.getStorageSync('token')
        },
        success:(res:any)=>{
          let data = res.data.data;
          if(data == null){
              toast.warning("收藏夹空空如也")
          }
          that.setData({data})
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
  clickToNav(e:any){
    var data = e.currentTarget.dataset.data;
    wx.navigateTo({
      url: '../detail/detail?title='+encodeURIComponent(data.title)+"&id="+encodeURIComponent(data.id)
    })
  },
})