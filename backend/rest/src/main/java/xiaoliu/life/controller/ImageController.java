package xiaoliu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xiaoliu.life.biz.ImageBiz;
import xiaoliu.life.model.dto.ResponseDto;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    ImageBiz imageBiz;

    @GetMapping("/getImageList")
    public ResponseDto getImageList(){
        return ResponseDto.Ok(imageBiz.getImageList());
    }
}
