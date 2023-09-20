package xiaoliu.life.common.util;

import org.springframework.web.client.RestTemplate;

public class RestTemplateUtil {
    public static RestTemplate restTemplate;
    public static  RestTemplate getInstance() {
        if (restTemplate != null){
            return restTemplate;
        } else {
            RestTemplate newRestTemplate = new RestTemplate();
            newRestTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
            restTemplate = newRestTemplate;
            return restTemplate;
        }
    }
}
