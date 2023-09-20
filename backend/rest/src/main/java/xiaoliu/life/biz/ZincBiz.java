package xiaoliu.life.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoliu.life.common.util.ZincUtil;
import xiaoliu.life.model.dto.ZincInsertDto;
import xiaoliu.life.model.dto.ZincResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ZincBiz {

    @Autowired
    ZincUtil zincUtil;

    public void insertData(ZincInsertDto zincInsertDto){
        zincUtil.insertData(zincInsertDto);
    }

    public List<ZincResponseDto> searchResult(String condition){
        String query = "{\n" +
                "    \"from\": 0,\n" +
                "    \"size\": 20,\n" +
                "    \"search_type\": \"querystring\",\n" +
                "    \"query\":{\n" +
                "        \"multi_match\":{\n" +
                "            \"query\":\""+condition+"\",\n" +
                "            \"fields\":[\"title\",\"content\"]\n" +
                "        }\n" +
                "    },\n" +
                "    \"highlight\": {\n" +
                "        \"fields\": {\n" +
                "            \"title\":{},\n" +
                "            \"content\":{}\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Map map = JSON.parseObject(query,new TypeReference<Map>(){});
        List<ZincResponseDto> zincResponseDtoList = new ArrayList<>();
        ((List<Map>)((Map)((Map)zincUtil.getSearchResult(map)).get("hits")).get("hits")).forEach(result->{
            String title = (String) ((Map)result.get("_source")).get("title");
            String id = (String) result.get("_id");
            String content = (String) ((Map)result.get("_source")).get("content");
            zincResponseDtoList.add(ZincResponseDto.builder()
                    .id(id)
                    .title(title)
                    .content(content)
                    .build());
        });
        return zincResponseDtoList.size() != 0 ? zincResponseDtoList:null;
    }
}
