package xiaoliu.life.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xiaoliu.life.model.entity.Openid2articleids;

public interface Openid2articleidsMapper extends BaseMapper<Openid2articleids> {
    String getStarList(String openid);
}
