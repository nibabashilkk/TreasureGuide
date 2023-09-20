package xiaoliu.life.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoliu.life.common.util.RedisUtil;
import xiaoliu.life.model.entity.Essay;
import xiaoliu.life.service.service.EssayService;

import java.util.List;

@Service
public class EssayBiz {

    @Autowired
    EssayService essayService;

    @Autowired
    RedisUtil redisUtil;

    public List<Essay> getEssay(){
        if (redisUtil.exist("essay")){
            return JSON.parseObject(redisUtil.stringGet("essay"),new TypeReference<List<Essay>>(){});
        } else {
            List<Essay> essayList = essayService.getEssay();
            redisUtil.stringSet("essay", JSON.toJSONString(essayList),3600);
            return essayList;
        }
    }

    public void addEssay(Essay essay){
        essayService.save(essay);
    }
}
