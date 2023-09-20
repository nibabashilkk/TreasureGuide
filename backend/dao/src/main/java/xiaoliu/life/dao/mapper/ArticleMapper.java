package xiaoliu.life.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xiaoliu.life.model.entity.Article;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {
    List<Article> getArticleByCategoryId( Integer categoryId);
}
