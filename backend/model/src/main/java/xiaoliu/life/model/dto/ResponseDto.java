package xiaoliu.life.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private Integer code;
    private String message;
    private Object data;

    public static ResponseDto Ok(Object t){
        return ResponseDto.builder().code(200).message("请求成功").data(t).build();
    }
    public static ResponseDto Ok(){
        return ResponseDto.builder().code(200).message("请求成功").build();
    }
    public static ResponseDto fail(Integer code,String message){
        return ResponseDto.builder().code(code).message(message).build();
    }
}
