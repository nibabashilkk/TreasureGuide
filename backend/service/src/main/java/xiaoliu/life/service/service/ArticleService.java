package xiaoliu.life.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xiaoliu.life.model.entity.Article;

import java.util.List;

public interface ArticleService extends IService<Article> {
    List<Article> getArticleByCategoryId(Integer articleId);
}
