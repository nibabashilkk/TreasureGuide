package xiaoliu.life.service.service.Impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoliu.life.dao.mapper.EssayMapper;
import xiaoliu.life.model.entity.Essay;
import xiaoliu.life.service.service.EssayService;

import java.util.List;

@Service
public class EssayServiceImpl extends ServiceImpl<EssayMapper, Essay> implements EssayService {
    @Autowired
    private EssayMapper essayMapper;

    @Override
    public List<Essay> getEssay() {
            List<Essay> essays = essayMapper.getEssay();
            return essays;
    }
}
