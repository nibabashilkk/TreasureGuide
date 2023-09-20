import toast from "../../components/toast/toast"
import login from "../../utils/login"
Page({

  /**
   * 页面的初始数据
   */
  data: {
      isAdmin:Boolean(false),
      categoryNameList:["常用工具","摸鱼神器","实用软件"],
      index:0,
      show:Boolean(true),
      title:'',
      data:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
      this.isAdmin();
  },
  isAdmin(){
      if(wx.getStorageSync("role") == "admin"){
          this.setData({
              "isAdmin":true
          })
      }
  },
  bindPickerChange(e:any){
      this.setData({
          index:e.detail.value
      })
  },
  onTitleChange(e:any){
      this.data.title = e.detail
  },
  onDataChange(e:any){
      this.data.data = e.detail
  },
  submit(){
      if(this,this.data.isAdmin){
          this.adminSubmit()
      }else{
          this.userSubmit()
      }
  },
  userSubmit(){
      wx.request({
          url:'http://127.0.0.1:8081/api/article/addUserArticle',
          method:"POST",
          header:{
            "satoken": wx.getStorageSync('token'),
            'content-type':"application/x-www-form-urlencoded"
          },
          data:{
            "title":this.data.title,
            "data":this.data.data,
          },
          success:(res:any)=>{
            if("code" in res.data){
                if(res.data.code == 200){
                    toast.success("提交成功")
                } else if(res.data.code == 401){
                    toast.warning("未登录，请重试")
                    login();
                } else if (res.data.code == 400 ){
                    toast.warning(res.data.message)
                } else {
                    toast.danger("其他错误")
                }
            }else{
                toast.danger("服务器错误")
            }
          },
          fail:(fail)=>{
              console.log(fail.errMsg)
          }
      })
  },
  adminSubmit(){
      wx.request({
          url:'http://127.0.0.1:8081/api/article/addAdminArticle',
          method:"POST",
          header:{
            "satoken": wx.getStorageSync('token'),
            'content-type':"application/x-www-form-urlencoded"
          },
          data:{
            "title":this.data.title,
            "data":this.data.data,
            "categoryId": Number(this.data.index) + 1
          },
          success:(res:any)=>{
            if("code" in res.data){
                if(res.data.code == 200){
                    toast.success("提交成功")
                } else if(res.data.code == 401){
                    toast.warning("未登录，请重试")
                    login();
                } else if (res.data.code == 400 ){
                    toast.warning(res.data.message)
                } else {
                    toast.danger("其他错误")
                }
            }else{
                toast.danger("服务器错误")
            }
          },
          fail:(fail)=>{
            console.log(fail.errMsg)
        }
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

  }
})