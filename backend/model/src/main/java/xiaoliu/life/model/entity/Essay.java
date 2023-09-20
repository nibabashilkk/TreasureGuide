package xiaoliu.life.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.ArrayTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("essay")
public class Essay implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @TableField(value = "img_list",typeHandler = ArrayTypeHandler.class,jdbcType = JdbcType.ARRAY)
    private Object imgList;
    private String content;
}
