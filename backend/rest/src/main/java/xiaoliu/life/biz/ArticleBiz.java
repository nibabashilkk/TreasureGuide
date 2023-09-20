package xiaoliu.life.biz;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoliu.life.common.util.RedisUtil;
import xiaoliu.life.model.dto.ZincInsertDto;
import xiaoliu.life.model.entity.Article;
import xiaoliu.life.model.entity.Openid2articleids;
import xiaoliu.life.service.service.ArticleService;
import xiaoliu.life.service.service.Openid2articleidsService;
import xiaoliu.life.service.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleBiz {

    @Autowired
    ArticleService articleService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    Openid2articleidsBiz openid2articleidsBiz;

    @Autowired
    UserService userService;

    @Autowired
    Openid2articleidsService openid2articleidsService;

    @Autowired
    ZincBiz zincBiz;

    public Article getArticleById(Integer articleId){
        String id = String.valueOf(articleId);
        if(redisUtil.exist(id)) {
            return JSON.parseObject(redisUtil.stringGet(id), new TypeReference<>(){});
        } else {
            Article article = articleService.getById(articleId);
            redisUtil.stringSet(id,JSON.toJSONString(article),3600);
            return article;
        }
    }

    public List<Article> getArticleByCategoryId(Integer categoryId){
        return articleService.getArticleByCategoryId(categoryId);
    }

    @Transactional
    public void addUserArticle(String title,String data){
        String openid = (String) StpUtil.getLoginId();
        Article article = Article.builder()
                        .openid(openid)
                        .categoryId(4)
                        .title(title)
                        .data(data)
                        .isShared(false)
                        .isVisible(false)
                        .build();
        articleService.save(article);
        openid2articleidsBiz.addData(openid,article.getId());
    }

    @Transactional
    public void addAdminArticle(String title,String data,Integer categoryId){
        StpUtil.checkRole("admin");
        Article article = Article.builder()
                .openid((String) StpUtil.getLoginId())
                .categoryId(categoryId)
                .title(title)
                .data(data)
                .isShared(true)
                .isVisible(true)
                .build();
        articleService.save(article);
        zincBiz.insertData(ZincInsertDto.builder()
                .id(String.valueOf(article.getId()))
                .title(article.getTitle())
                .content(article.getData())
                .build());
    }

    public List<Article> getArticleByOpenid(String openid){
        List<Integer> articleIdList = openid2articleidsBiz.getIdFromRedisByOpenid(openid);
        if(articleIdList == null || articleIdList.isEmpty()){
            return null;
        } else {
            List<Article> articleList = articleService.listByIds(articleIdList);
            if(articleIdList.size() != articleList.size()){
                List<Integer> articleIds = new ArrayList<>();
                articleList.forEach(article -> {
                    articleIds.add(article.getId());
                });
                Integer id = userService.getUserByOpenid(openid).getId();
                Openid2articleids openid2articleids = Openid2articleids.builder()
                        .id(id)
                        .openid(openid)
                        .articleIds(JSON.toJSONString(articleIds))
                        .build();
                openid2articleidsService.saveOrUpdate(openid2articleids);
                redisUtil.delete(openid);
            }
            return articleList;
        }
    }
}
