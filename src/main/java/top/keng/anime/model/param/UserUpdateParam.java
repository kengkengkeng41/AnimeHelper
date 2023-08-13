/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本软件已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2021 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package top.keng.anime.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.sql.Blob;

/**
 * 用户修改param
 */
@Data
public class UserUpdateParam implements Serializable {

    @ApiModelProperty("登录名")
    private String loginName;
    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户密码(需要MD5加密)")
    private String password;

    @ApiModelProperty("权限")
    private String role;



}
