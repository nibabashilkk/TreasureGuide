package xiaoliu.life.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoliu.life.common.util.RedisUtil;
import xiaoliu.life.model.entity.Article;
import xiaoliu.life.model.entity.Openid2articleids;
import xiaoliu.life.service.service.ArticleService;
import xiaoliu.life.service.service.Openid2articleidsService;
import xiaoliu.life.service.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class Openid2articleidsBiz {

    @Autowired
    Openid2articleidsService openid2articleidsService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    public List<Integer> getIdFromRedisByOpenid(String openid){
        if (redisUtil.exist(openid)){
            Set<String> dataSet = redisUtil.setGet(openid);
            List<Integer> dataList = new ArrayList<>();
            dataSet.forEach(data ->{
                dataList.add(Integer.valueOf(data));
            });
            return dataList;
        } else {
            List<Integer> articleIdList = JSON.parseObject(openid2articleidsService.getStartList(openid),new TypeReference<List<Integer>>(){});
            if (articleIdList == null){
                return null;
            }
            articleIdList.forEach(articleId ->{
                redisUtil.setSet(openid,articleId);
            });
            return articleIdList;
        }
    }

    @Transactional
    public void addData(String openid, Integer articleId){
        Integer id = userService.getUserByOpenid(openid).getId();
        List<Integer> articleIdList = getIdFromRedisByOpenid(openid);
        if (articleIdList == null){
            articleIdList = new ArrayList<>();
        }
        articleIdList.add(articleId);
        Openid2articleids openid2articleids = Openid2articleids.builder()
                                .id(id)
                                .openid(openid)
                                .articleIds(JSON.toJSONString(articleIdList))
                                .build();
        openid2articleidsService.saveOrUpdate(openid2articleids);
        redisUtil.delete(openid);
    }

    @Transactional
    public void deleteData(String openid, Integer articleId){
        Integer id = userService.getUserByOpenid(openid).getId();
        List<Integer> articleIdList = getIdFromRedisByOpenid(openid);
        articleIdList.remove(articleId);
        Openid2articleids openid2articleids = Openid2articleids.builder()
                .id(id)
                .openid(openid)
                .articleIds(JSON.toJSONString(articleIdList))
                .build();
        openid2articleidsService.saveOrUpdate(openid2articleids);
        redisUtil.delete(openid);
        Article article = articleService.getById(articleId);
        if( article.getOpenid().equals(openid) && (article.getIsShared() == false) && (article.getIsVisible() == false)){
            articleService.removeById(article);
        }
    }
}
