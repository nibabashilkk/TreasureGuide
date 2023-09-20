package xiaoliu.life.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xiaoliu.life.model.entity.Essay;

import java.util.List;

public interface EssayService extends IService<Essay> {
    List<Essay> getEssay();
}
