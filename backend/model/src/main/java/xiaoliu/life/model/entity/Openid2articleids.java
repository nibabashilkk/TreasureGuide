package xiaoliu.life.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("openid2articleids")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Openid2articleids {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String openid;
    private String articleIds;
}
