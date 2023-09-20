package xiaoliu.life.service.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xiaoliu.life.dao.mapper.CategoryMapper;
import xiaoliu.life.model.entity.Category;
import xiaoliu.life.service.service.CategoryService;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
