// index.ts
// 获取应用实例
import login from "../../utils/login"
Page({
  data: {
      categoryList:[],
      isShow:Boolean(true),
      data: null,
      condition:''
  },
  onLoad() {
    login();
    let that = this;
    wx.request({
        url:'http://127.0.0.1:8081/api/category/getAllCategory',
        success: function(res:any){
            let categoryList = res.data.data;
            that.setData({categoryList})
        }
    })
  },
  TimeID: -1,
  onChange(e:any){
    this.data.condition = e.detail;
    if(e.detail.length == 0){
        this.setData({
            "isShow":true
        })
    } else {
        if(this.data.isShow == true){
            this.setData({
                "isShow":false
            })
        }
    }
    clearTimeout(this.TimeID);
    this.TimeID = setTimeout(()=>{
        wx.request({
            url:"http://127.0.0.1:8081/api/article/search",
            method:"GET",
            data:{
                "condition":e.detail
            },
            header:{
              'content-type':"application/x-www-form-urlencoded"
            },
            success:(res:any)=>{
                let data = res.data.data;
                this.setData({
                    "data":data as any
                })
            }
        })
    },1000)
  },
  onFocue(){
      if(this.data.isShow == true){
        this.setData({
            "isShow":false
        })
      }
  },
  onBlur(){
      if(this.data.condition.length == 0 && this.data.isShow == false){
        this.setData({
            "isShow":true
        })
      }
  },
  clickToNav(e:any){
    var category = e.currentTarget.dataset.category;
    wx.navigateTo({
      url: '../category/category?id='+encodeURIComponent(category.id)+"&categoryName="+encodeURIComponent(category.categoryName)
    })
  },
  clickToDetail(e:any){
    var data = e.currentTarget.dataset.data;
    wx.navigateTo({
        url: '../detail/detail?id='+encodeURIComponent(data.id)+"&title="+encodeURIComponent(data.title)
      })
  },
  onShareAppMessage() {

},
})
