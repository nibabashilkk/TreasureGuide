package xiaoliu.life.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xiaoliu.life.model.entity.Essay;

import java.util.List;

public interface EssayMapper extends BaseMapper<Essay> {
    List<Essay> getEssay();
}
