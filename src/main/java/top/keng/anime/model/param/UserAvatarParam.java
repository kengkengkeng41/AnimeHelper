package top.keng.anime.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class UserAvatarParam implements Serializable {
    @ApiModelProperty("头像数据")
    @NotEmpty(message = "数据啊！！！！")
    private String avatar;

    @ApiModelProperty("文件名称")
    @NotEmpty(message = "。。。。。。")
    private String name;

}
