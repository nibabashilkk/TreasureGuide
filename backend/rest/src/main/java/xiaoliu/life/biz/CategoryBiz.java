package xiaoliu.life.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoliu.life.model.entity.Category;
import xiaoliu.life.service.service.CategoryService;

import java.util.List;

@Service
public class CategoryBiz {
    @Autowired
    CategoryService categoryService;

    public List<Category> getCategoryList(){
        return categoryService.list();
    }

    public void addCategory(Category category){
        categoryService.save(category);
    }
}
