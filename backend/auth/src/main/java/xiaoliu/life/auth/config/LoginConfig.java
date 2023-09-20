package xiaoliu.life.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "login")
@Data
public class LoginConfig {
    private String AppID;
    private String AppSecret;
}
