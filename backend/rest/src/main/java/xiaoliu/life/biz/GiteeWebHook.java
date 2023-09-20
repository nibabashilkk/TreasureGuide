package xiaoliu.life.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class GiteeWebHook {
    public void deploy(){
        try {
            String[] cmd = {"/bin/sh","-c","/usr/local/nginx/html/hexo/update.sh"};
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);
            processBuilder.start();
        } catch (IOException e) {
            log.error("执行命令失败",e);
        }
        log.info("成功");
    }

}
