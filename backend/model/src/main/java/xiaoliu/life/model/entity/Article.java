package xiaoliu.life.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("article")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer categoryId;
    private String title;
    private String data;
    private String openid;
    private Boolean isShared;
    private Boolean isVisible;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
