package xiaoliu.life.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoliu.life.biz.Openid2articleidsBiz;
import xiaoliu.life.model.dto.ResponseDto;

@RestController
@RequestMapping("/openid2articleids")
public class Openid2articleidsController {

    @Autowired
    Openid2articleidsBiz openid2articleidsBiz;

    @SaCheckLogin
    @PostMapping("/add")
    public ResponseDto addData(@RequestBody Integer articleId){
        String openid = (String) StpUtil.getLoginId();
        openid2articleidsBiz.addData(openid,articleId);
        return ResponseDto.Ok();
    }

    @SaCheckLogin
    @GetMapping("/getArticleIds")
    public ResponseDto getArticleIds(){
        String openid = (String) StpUtil.getLoginId();
        return ResponseDto.Ok(openid2articleidsBiz.getIdFromRedisByOpenid(openid));
    }

    @SaCheckLogin
    @PostMapping("/delete")
    public ResponseDto deleteData(@RequestBody Integer articleId){
        String openid = (String) StpUtil.getLoginId();
        openid2articleidsBiz.deleteData(openid,articleId);
        return ResponseDto.Ok();
    }
}
