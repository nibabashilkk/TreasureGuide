package xiaoliu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xiaoliu.life.biz.CategoryBiz;
import xiaoliu.life.model.dto.ResponseDto;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryBiz categoryBiz;

    @GetMapping("/getAllCategory")
    public ResponseDto getAllCategory(){
        return ResponseDto.Ok(categoryBiz.getCategoryList());
    }
}
