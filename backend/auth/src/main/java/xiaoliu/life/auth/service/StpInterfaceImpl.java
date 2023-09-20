package xiaoliu.life.auth.service;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xiaoliu.life.model.entity.User;
import xiaoliu.life.service.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    UserService userService;
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        User user = userService.getUserByOpenid((String) loginId);
        List<String> roleList = new ArrayList<>();
        roleList.add(user.getRoleCode());
        return roleList;
    }
}
