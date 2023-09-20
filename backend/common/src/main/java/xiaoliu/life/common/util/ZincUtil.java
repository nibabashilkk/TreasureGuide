package xiaoliu.life.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xiaoliu.life.common.config.ZincProperties;
import xiaoliu.life.model.dto.ZincInsertDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class ZincUtil {

    @Autowired
    ZincProperties zincProperties;

    @Autowired
    RestTemplate restTemplate;

    public Object getSearchResult(Map query){
        String uri = zincProperties.getIp()+"/es/"+zincProperties.getIndex()+"/_search";
        String auth = "Basic "+ Base64Util.base64Encode(zincProperties.getUsername()+":"+zincProperties.getPassword());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",auth);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(query,httpHeaders);
        Object responseDto = restTemplate.postForObject(uri,httpEntity, Object.class);
        return responseDto;
    }
    public void insertData(ZincInsertDto zincInsertDto){
        String uri = zincProperties.getIp()+"/api/"+zincProperties.getIndex()+"/_doc/"+zincInsertDto.getId();
        String auth = "Basic "+ Base64Util.base64Encode(zincProperties.getUsername()+":"+zincProperties.getPassword());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",auth);
        Map<String,String> map = new HashMap<>();
        map.put("title",zincInsertDto.getTitle());
        map.put("content",zincInsertDto.getContent());
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(map,httpHeaders);
        restTemplate.put(uri,httpEntity);
    }
}
