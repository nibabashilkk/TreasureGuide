package xiaoliu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xiaoliu.life.biz.GiteeWebHook;

@RestController
public class GiteeWebHookController {

    @Autowired
    GiteeWebHook giteeWebHook;

    @PostMapping("/giteeWebHook")
    public void handleGiteeWebHook(@RequestBody Object object){
        giteeWebHook.deploy();
    }
}
