package xiaoliu.life.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xiaoliu.life.model.entity.Openid2articleids;

import java.util.List;

public interface Openid2articleidsService extends IService<Openid2articleids> {
    String getStartList(String openid);
}
