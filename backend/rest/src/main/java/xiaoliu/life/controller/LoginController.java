package xiaoliu.life.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoliu.life.biz.UserBiz;
import xiaoliu.life.model.dto.ResponseDto;
import xiaoliu.life.biz.LoginBiz;
import xiaoliu.life.model.entity.User;

@RestController
public class LoginController {

    @Autowired
    LoginBiz loginBiz;

    @Autowired
    UserBiz userBiz;

    @PostMapping("/login")
    public ResponseDto login(@RequestBody String code){
        return ResponseDto.Ok(loginBiz.login(code));
    }

    @SaCheckLogin
    @GetMapping("/getOpenid")
    public ResponseDto getOpenid(){
        return ResponseDto.Ok(loginBiz.getOpenid());
    }

    @SaCheckLogin
    @GetMapping("/getUser")
    public ResponseDto getUser(){
        String openid = (String) StpUtil.getLoginId();
        return ResponseDto.Ok(userBiz.getUser(openid));
    }

    @PostMapping("/backLogin")
    public ResponseDto backLogin(@RequestParam(value = "openid") String openid){
        User user = userBiz.getUser(openid);
        if(user != null && user.getRoleCode().equals("admin")){
            StpUtil.login(openid);
            return ResponseDto.Ok(StpUtil.getTokenValue());
        } else {
            return ResponseDto.Ok(ResponseDto.fail(400,"认证失败"));
        }
    }
}
