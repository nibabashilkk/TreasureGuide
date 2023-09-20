function login(){
    wx.login({
        success(res){
            if(res.code){
                wx.request({
                    url: "http://127.0.0.1:8081/api/login",
                    method:"POST",
                    data: res.code,
                    success:(res:any)=>{
                        if(("code" in res.data) && res.data.code == 200){
                            wx.setStorageSync("token",res.data.data);
                            wx.request({
                                url:"http://127.0.0.1:8081/api/getUser",
                                header:{
                                  "satoken": res.data.data
                                },
                                success:(res:any)=>{
                                    if(("code" in res.data) && res.data.code == 200){
                                        wx.setStorageSync("role",res.data.data.roleCode);
                                    } 
                                }
                            })
                        }
                    }
                })
            } else {
                console.log("登录失败" + res.errMsg)
            }
        }
    });
}
export default login;