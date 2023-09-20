package xiaoliu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoliu.life.biz.EssayBiz;
import xiaoliu.life.model.dto.ResponseDto;
import xiaoliu.life.model.entity.Essay;

@RestController
@RequestMapping("/essay")
public class EssayController {

    @Autowired
    EssayBiz essayBiz;

    @GetMapping("/get")
    public ResponseDto getEssay(){
        return ResponseDto.Ok(essayBiz.getEssay());
    }

    @PostMapping("/add")
    public ResponseDto addEssay(@RequestBody Essay essay){
        essayBiz.addEssay(essay);
        return ResponseDto.Ok();
    }
}
