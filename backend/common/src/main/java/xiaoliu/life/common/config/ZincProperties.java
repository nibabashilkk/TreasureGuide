package xiaoliu.life.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "zinc")
@Component
@Data
public class ZincProperties {
    private String username;
    private String password;
    private String ip;
    private String index;
}
