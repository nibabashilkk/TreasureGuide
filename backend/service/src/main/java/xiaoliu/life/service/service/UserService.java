package xiaoliu.life.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xiaoliu.life.model.entity.User;

public interface UserService extends IService<User> {
    User getUserByOpenid(String openid);
}
