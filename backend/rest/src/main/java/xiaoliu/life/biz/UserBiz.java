package xiaoliu.life.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoliu.life.model.entity.User;
import xiaoliu.life.service.service.UserService;

@Service
public class UserBiz {
    @Autowired
    UserService userService;

    public User getUser(String openid) {
        return userService.getUserByOpenid(openid);
    }
}
