import toast from "../../components/toast/toast"
Page({

    /**
     * 页面的初始数据
     */
    data: {
        article:{
            id:Number(),
            categoryId:Number(),
            title:String(),
            data:String(),
            isShared:Boolean(),
            isVisible: Boolean(),
            createTime:Date(),
            updateTime:Date(),
            openid:String()
        },
        isShow:Boolean(true),
        btns: [
            {
              icon: "icon-xihuan",
              color: "",
              type: "like"
            },
            {
              icon: "icon-fuzhi",
              color:"",
              type: "copy"
            },
            {
                icon:"icon-gongkai",
                color:"",
                type: "share"
            }
          ],
        articleIds:[],
        isLike:false,
        isShared:false,
        articleId:Number()
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        let articleId = Number(decodeURIComponent(options.id as string));
        this.data.articleId = articleId;
        let title = decodeURIComponent(options.title as string)
        wx.setNavigationBarTitle({
            title: title+" | 宝藏指北",
        })
    },
    copy(){
        let data = String(this.data.article.data);
        wx.setClipboardData({
            data: data
        })
    },
    like(){
        if (this.data.isLike == true){
            this.deleteArticleIdToLike(this.data.article.id).then((res:any)=>{
                if(("code" in res) && res.code == 200){
                    this.data.isLike = false;
                    this.setData({
                        "btns[0].color":""
                    });
                    toast.success("取消收藏成功")
                } else {
                    toast.danger("取消收藏失败")
                }
            })
        } else {
            this.addArticleIdToLike(this.data.article.id).then((res:any)=>{
                if (("code" in res) && res.code == 200){
                    this.data.isLike = true;
                    this.setData({
                        "btns[0].color":"red"
                    });
                    toast.success("收藏成功")
                } else {
                    toast.danger("收藏失败")
                }
            });
        }
    },
    share(){
        let that = this;
        wx.request({
            url:"http://127.0.0.1:8081/api/getOpenid",
            header:{
                "satoken": wx.getStorageSync('token')
            },
            success:(res:any)=>{
                if("code" in res.data){
                    if(res.data.code == 200){
                        let openid = res.data.data;
                        if(that.data.article.openid == openid){
                            if(that.data.article.isShared == false){
                                wx.showModal({
                                    title:"提示",
                                    content:"确定共享后所有人都能看到本页内容",
                                    success(res){
                                        if(res.confirm){
                                            that.data.article.isShared = true;
                                            that.setData({
                                                'btns[2].color':'green'
                                            })
                                        }
                                    }
                                })
                            } else {
                                toast.warning("暂不支持取消共享")
                            }
                        }else{
                            toast.warning("你没有权限修改")
                        }
                    } else if(res.data.code == 401){
                        toast.warning("未登录，将会自动登录请重试")
                    } else {
                        toast.danger("其他错误");
                    }
                } else {
                    toast.danger("服务器错误")
                }
            }
        })
    },
    async addArticleIdToLike(articleId:Number){
        let res = 
        await new Promise((resolve,reject)=>{
            wx.request({
                url:"http://127.0.0.1:8081/api/openid2articleids/add",
                method:"POST",
                header:{
                    "satoken": wx.getStorageSync('token')
                },
                data: articleId,
                success:(res)=>{
                    resolve(res.data)
                },
                fail:(fail)=>{
                    reject(fail.errMsg)
                }
            })
        })
        return res;
    },
    async deleteArticleIdToLike(articleId:Number) {
        let res = 
        await new Promise((resolve,reject)=>{
            wx.request({
                url:"http://127.0.0.1:8081/api/openid2articleids/delete",
                method:"POST",
                header:{
                    "satoken": wx.getStorageSync('token')
                },
                data:articleId,
                success:(res)=>{
                    resolve(res.data)
                },
                fail:(fail)=>{
                    reject(fail.errMsg)
                }
            })
        })
        return res;

    },
    handleBarBtnClick(e: WechatMiniprogram.CustomEvent) {
        const { type } = e.detail
        switch (type) {
            case 'like':
                this.like();
                break;
            case 'copy':
                this.copy();
                break;
            case 'share':
                this.share()
        }
  
      },
      async getArticleIds(){
        let res = await new Promise((resolve,reject)=>{
                wx.request({
                    url:"http://127.0.0.1:8081/api/openid2articleids/getArticleIds",
                    header:{
                        "satoken": wx.getStorageSync('token')
                    },
                    success:(res:any)=>{
                        resolve(res.data)
                    },
                    fail:(fail:any)=>{
                        reject(fail.data)
                    }
                })
            })
        return res;
      },
      getArticle(articleId:Number){
          let that = this;
          wx.request({
              url:"http://127.0.0.1:8081/api/article/articleId",
              method:"POST",
              data: articleId,
              success:(res:any)=>{
                  let article = res.data.data;
                  that.setData({article});
                  if (article.isShared == true){
                      that.setData({
                          "btns[2].color":"green"
                      })
                  }
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
        if(wx.getStorageSync("role") == "admin"){
            this.setData({
                "isShow":false
            })
        }
        this.getArticle(this.data.articleId);
        this.getArticleIds().then((res:any)=>{
            let articleIds = res.data;
            if(articleIds.indexOf(this.data.articleId) != -1){
                this.data.isLike = true;
                this.setData({
                    "btns[0].color":"red"
                })
            }
        });
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