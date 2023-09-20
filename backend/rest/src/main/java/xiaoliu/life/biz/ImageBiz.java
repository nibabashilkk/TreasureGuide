package xiaoliu.life.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoliu.life.model.entity.Image;
import xiaoliu.life.service.service.ImageService;

import java.util.List;

@Service
public class ImageBiz {
    @Autowired
    ImageService imageService;

    public List<Image> getImageList(){
        return imageService.list();
    }
}
