package xiaoliu.life.service.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoliu.life.dao.mapper.UserMapper;
import xiaoliu.life.model.entity.User;
import xiaoliu.life.service.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByOpenid(String openid) {
        return userMapper.getUserByOpenid(openid);
    }
}
