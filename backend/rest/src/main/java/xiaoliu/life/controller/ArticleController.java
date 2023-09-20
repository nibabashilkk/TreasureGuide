package xiaoliu.life.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoliu.life.biz.Openid2articleidsBiz;
import xiaoliu.life.biz.ZincBiz;
import xiaoliu.life.model.dto.ResponseDto;
import xiaoliu.life.biz.ArticleBiz;
import xiaoliu.life.model.dto.ZincInsertDto;
import xiaoliu.life.model.entity.Article;
import xiaoliu.life.service.service.ArticleService;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleBiz articleBiz;

    @Autowired
    Openid2articleidsBiz openid2articleidsBiz;

    @Autowired
    ZincBiz zincBiz;

    @Autowired
    ArticleService articleService;

    @PostMapping("/categoryId")
    public ResponseDto getArticleByCategoryId(@RequestBody Integer categoryId){
        List<Article> articleList = articleBiz.getArticleByCategoryId(categoryId);
        return ResponseDto.Ok((articleList.isEmpty() ? null : articleList));
    }

    @PostMapping("/articleId")
    public ResponseDto getArticleById(@RequestBody Integer articleId){
        return ResponseDto.Ok(articleBiz.getArticleById(articleId));
    }

    @SaCheckLogin
    @GetMapping("/star")
    public ResponseDto getArticleByOpenId(){
        String openid = (String) StpUtil.getLoginId();
        return ResponseDto.Ok(articleBiz.getArticleByOpenid(openid));
    }

    @SaCheckLogin
    @PostMapping("/addUserArticle")
    public ResponseDto addUserArticle(@RequestParam(value = "title") String title,@RequestParam(value = "data") String data){
        if(StringUtils.isEmpty(title)||StringUtils.isEmpty(data)){
            return ResponseDto.fail(400,"提交内容都不能为空");
        }
        articleBiz.addUserArticle(title,data);
        return ResponseDto.Ok();
    }

    @PostMapping("/addAdminArticle")
    public  ResponseDto addAdminArticle(@RequestParam(value = "title") String title,@RequestParam(value = "data") String data,@RequestParam(value = "categoryId") Integer categoryId){
        if(StringUtils.isEmpty(title)||StringUtils.isEmpty(data)){
            return ResponseDto.fail(400,"提交内容都不能为空");
        }
        articleBiz.addAdminArticle(title,data,categoryId);
        return ResponseDto.Ok();
    }

    @GetMapping("/search")
    public ResponseDto searchArticle(@RequestParam(value = "condition") String condition){
        return ResponseDto.Ok(zincBiz.searchResult(condition));
    }

    @GetMapping("/test")
    public ResponseDto test(){
        articleService.list().forEach(article -> {
            ZincInsertDto zincInsertDto = ZincInsertDto.builder().id(String.valueOf(article.getId())).title(article.getTitle()).content(article.getData()).build();
            zincBiz.insertData(zincInsertDto);
        });
        return ResponseDto.Ok();
    }
}
