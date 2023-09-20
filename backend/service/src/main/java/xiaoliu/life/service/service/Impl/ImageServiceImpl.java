package xiaoliu.life.service.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xiaoliu.life.dao.mapper.ImageMapper;
import xiaoliu.life.model.entity.Image;
import xiaoliu.life.service.service.ImageService;

@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {
}
