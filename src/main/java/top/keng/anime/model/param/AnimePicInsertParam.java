package top.keng.anime.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class AnimePicInsertParam implements Serializable{

        @ApiModelProperty("图片数据")
        @NotEmpty(message = "数据呢？")
        private String picture;

        @ApiModelProperty("图片名称")
        @NotEmpty(message = "？")
        private String filename;


}
