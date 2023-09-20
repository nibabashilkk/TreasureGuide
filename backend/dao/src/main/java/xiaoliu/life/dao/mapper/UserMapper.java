package xiaoliu.life.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xiaoliu.life.model.entity.User;

public interface UserMapper extends BaseMapper<User> {
    User getUserByOpenid(String openid);
}
