package top.keng.anime.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.keng.anime.common.ServiceResultEnum;
import top.keng.anime.mapper.UserTokenMapper;
import top.keng.anime.mapper.VisitorMapper;
import top.keng.anime.model.UserToken;
import top.keng.anime.model.Visitor;
import top.keng.anime.model.param.UserUpdateParam;
import top.keng.anime.service.VisitorService;
import top.keng.anime.utils.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorMapper vm;
    @Autowired
    private UserTokenMapper utm;

    @Override
    public Result register(String loginName, String password) {
        if (vm.selectByLoginName(loginName) != null) {
            return ResultGenerator.genErrorResult(600, ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult());
        }
        Visitor v = new Visitor();
        v.setLoginName(loginName);
        v.setNickName(loginName);
        String passwordMD5 = SecurityUtils.encodePassword(password);
        v.setPasswordMd5(passwordMD5);
        if (vm.insertSelective(v) > 0) {
            // 注册不返回对象，只返回成功信息
            return ResultGenerator.genSuccessResult("注册成功");
        }
        return ResultGenerator.genErrorResult(603, "注册末端");
    }

    @Override
    public Result login(String loginName, String password) {
        Visitor v1 = vm.selectByLoginName(loginName);
        if (v1 == null) {
            return ResultGenerator.genErrorResult(601, ServiceResultEnum.USER_NOT_EXIST.getResult());
        }
        else {
            if (!SecurityUtils.matchesPassword(password,v1.getPasswordMd5())) {
                return ResultGenerator.genErrorResult(602, ServiceResultEnum.USER_PASSWORD_WRONG.getResult()); //602
            }
            String token = getNewToken(System.currentTimeMillis() + "",v1.getUserId());
            UserToken userToken = utm.selectByPrimaryKey(v1.getUserId());
            Date now = new Date();
            Date expireTime = new Date(now.getTime() + 8 * 24 * 3600 * 1000);
            if (userToken == null) {
                userToken = new UserToken();
                userToken.setUserId(v1.getUserId());
                userToken.setToken(token);
                userToken.setInsertTime(now);
                userToken.setExpireTime(expireTime);
                if (utm.insertSelective(userToken) > 0) {
                    //登录返回用户token
                    return ResultGenerator.genSuccessResult("successfully generate token#"+userToken.getUserId(),userToken);
                }
            } else {
                userToken.setToken(token);
                userToken.setInsertTime(now);
                userToken.setExpireTime(expireTime);
                if (utm.updateByPrimaryKeySelective(userToken) > 0) {
                    return ResultGenerator.genSuccessResult("successfully update token#"+userToken.getUserId(),userToken);
                }
            }
        }
        return ResultGenerator.genErrorResult(603, "MoDuan"); //603
    }
    private String getNewToken(String timeStr, Long userId) {
        String src = timeStr + userId + NumberUtil.genRandomNum(4);
        return SystemUtil.genToken(src);
    }

    @Override
    public Result updateVisitorInfo(UserUpdateParam userParam, Long userId) {
        Visitor v = vm.selectByPrimaryKey(userId);
        if (v == null) {
            return ResultGenerator.genErrorResult(601, ServiceResultEnum.USER_NOT_EXIST.getResult());
        }
        v.setNickName(userParam.getNickName());
        v.setLoginName(userParam.getLoginName());
        //user.setPasswordMd5(mallUser.getPasswordMd5());
        //若密码为空字符，则表明用户不打算修改密码，使用原密码保存
        if ( !userParam.getPassword().equals("") ){
            v.setPasswordMd5(SecurityUtils.encodePassword(userParam.getPassword()));
        }
        v.setRole(userParam.getRole());
        if (vm.updateByPrimaryKeySelective(v) > 0) {
            return ResultGenerator.genSuccessResult("成功更改用户信息#" + userId, v);
        }
        return ResultGenerator.genErrorResult(603, "更新信息末端");
    }

    @Override
    public Result updateAvatar(String avatar, String fileName, Long userId) throws IOException {
        Visitor v = vm.selectByPrimaryKey(userId);
        if (v == null) {
            return ResultGenerator.genErrorResult(601, ServiceResultEnum.USER_NOT_EXIST.getResult());
        }
        String path = UploadUtil.avatarUpload(UploadUtil.base64ToMultipart(avatar), v.getLoginName(), fileName);
        UploadUtil.avatarDelete(v.getAvatar());
        v.setAvatar(path);
        log.info(path);
        if (vm.updateByPrimaryKeySelective(v) > 0){
            return ResultGenerator.genSuccessResult("成功更新用户头像#"+userId+"\n"+v.getAvatar(),v);
        }
        return ResultGenerator.genErrorResult(603, "更新头像末端");
    };
    @Override
    public Result logout(Long userId) {
        if (utm.deleteByPrimaryKey(userId) > 0){
            return ResultGenerator.genSuccessResult("成功登出#"+userId);
        }
        return ResultGenerator.genErrorResult(603, "登出末端");
    }

    @Override
    public ResultList getVisitorsList() {
        List<Visitor> v = vm.findVisitorList();
        return ResultListGenerator.genSuccessResult("当前用户数："+v.size(), v);
    }

}
