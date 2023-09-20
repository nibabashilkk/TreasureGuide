package xiaoliu.life.biz;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import xiaoliu.life.auth.config.LoginConfig;
import xiaoliu.life.common.util.RestTemplateUtil;
import xiaoliu.life.model.entity.User;
import xiaoliu.life.service.service.UserService;

import java.util.Map;

@Service
public class LoginBiz {
    @Autowired
    UserService userService;

    @Autowired
    LoginConfig loginConfig;

    public String login(String code){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + loginConfig.getAppID()
                + "&secret=" + loginConfig.getAppSecret()
                + "&js_code=" + code
                + "&grant_type=authorization_code";
        Map<String,String> data = RestTemplateUtil.getInstance().getForObject(url, Map.class);
        String openid = data.get("openid");
        if(StringUtils.isEmpty(openid)){
            return null;
        } else {
            if(userService.getUserByOpenid(openid) == null){
                User user = User.builder()
                        .openid(openid)
                        .roleCode("user")
                        .unionid(data.get("unionid"))
                        .build();
                userService.save(user);
            }
            StpUtil.login(openid);
            return StpUtil.getTokenValue();
        }
    }

    public String getOpenid(){
        return (String) StpUtil.getLoginId();
    }
}
