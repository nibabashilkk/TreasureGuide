package xiaoliu.life.common.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {

    public static String base64Decode(String blogIdBase64){
        byte[] result = Base64.getDecoder().decode(blogIdBase64);
        return new String(result);
    }
    public static String base64Encode(String s){
        byte[] result = Base64.getEncoder().encode(s.getBytes(StandardCharsets.UTF_8));
        return new String(result);
    }
}
