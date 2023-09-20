package xiaoliu.life.config;
import cn.dev33.satoken.exception.NotLoginException;

import cn.dev33.satoken.exception.NotRoleException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xiaoliu.life.model.dto.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NotLoginException.class})
    public ResponseDto NotLoginException(NotLoginException exception){
        return ResponseDto.fail(401,"未登录");
    }

    @ExceptionHandler(value = {NotRoleException.class})
    public ResponseDto NotRoleException(NotRoleException e){
        return ResponseDto.fail(400,"没有管理员权限");
    }
}
