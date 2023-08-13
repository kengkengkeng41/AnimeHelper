package top.keng.anime.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@Data
public class AnimePicParam implements Serializable{

        @ApiModelProperty("图片数据")
        @NotEmpty(message = "数据呢？")
        private String picture;

        @ApiModelProperty("图片名称")
        @NotEmpty(message = "？")
        private String filename;

        @ApiModelProperty("p1/p2?")
        @NotEmpty(message = "p1为主视觉图,p2为宽壁纸")
        private String type;

        @ApiModelProperty("动漫编号")
        @NotEmpty(message = "...")
        private Short number;

}
