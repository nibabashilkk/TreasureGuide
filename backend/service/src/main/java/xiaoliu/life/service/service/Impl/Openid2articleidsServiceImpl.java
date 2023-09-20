package xiaoliu.life.service.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoliu.life.dao.mapper.Openid2articleidsMapper;
import xiaoliu.life.model.entity.Openid2articleids;
import xiaoliu.life.service.service.Openid2articleidsService;

import java.util.List;

@Service
public class Openid2articleidsServiceImpl extends ServiceImpl<Openid2articleidsMapper, Openid2articleids> implements Openid2articleidsService {

    @Autowired
    Openid2articleidsMapper openid2articleidsMapper;
    @Override
    public String getStartList(String openid) {
        return openid2articleidsMapper.getStarList(openid);
    }
}
