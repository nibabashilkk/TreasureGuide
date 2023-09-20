package xiaoliu.life.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("image")
@Data
public class Image {
    @TableId
    private Integer id;
    private String imgUrl;
}
