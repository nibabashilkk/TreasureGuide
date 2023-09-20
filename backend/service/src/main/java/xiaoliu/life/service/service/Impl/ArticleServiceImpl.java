package xiaoliu.life.service.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoliu.life.dao.mapper.ArticleMapper;
import xiaoliu.life.model.entity.Article;
import xiaoliu.life.service.service.ArticleService;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public List<Article> getArticleByCategoryId(Integer articleId) {
        return articleMapper.getArticleByCategoryId(articleId);
    }
}
