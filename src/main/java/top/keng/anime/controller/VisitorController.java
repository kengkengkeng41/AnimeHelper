package top.keng.anime.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import top.keng.anime.config.annotation.TokenToUser;
import top.keng.anime.model.param.UserAvatarParam;
import top.keng.anime.model.param.UserLoginParam;
import top.keng.anime.model.Visitor;
import top.keng.anime.model.param.UserRegisterParam;
import top.keng.anime.model.param.UserUpdateParam;
import top.keng.anime.service.VisitorService;
import top.keng.anime.utils.Result;

import top.keng.anime.utils.ResultGenerator;
import top.keng.anime.utils.ResultList;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;


@Slf4j
@RestController
@Api(value = "api", tags = "用户操作接口")
//@RequestMapping("/api")
public class VisitorController {
    @Resource
    private VisitorService vs;

    private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

    @PostMapping("/user/login")
    @ApiOperation(value = "登录接口", notes = "返回token")
    public Result<String> login(@RequestBody @Valid UserLoginParam userLoginParam) {
        Result loginResult = vs.login(userLoginParam.getLoginName(), userLoginParam.getPassword());
        logger.info("login api,loginName={},loginResult={}", userLoginParam.getLoginName(), loginResult.getData());
        return loginResult;
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "用户注册", notes = "")
    public Result<String> register(@RequestBody @Valid UserRegisterParam userRegisterParam){
        Result registerResult = vs.register(userRegisterParam.getLoginName(), userRegisterParam.getPassword());
        logger.info("register api,loginName={},registerResult={}", userRegisterParam.getLoginName(), registerResult.getMessage());
        return registerResult;
    }

    @PostMapping("/user/info")
    @ApiOperation(value = "用户信息修改", notes = "需要token")
    public Result<String> updateInfo(@RequestBody @Valid UserUpdateParam userUpdateParam, @TokenToUser Visitor visitor) {
        Result updateResult = vs.updateVisitorInfo(userUpdateParam, visitor.getUserId());
        logger.info("register api,nickName={},loginResult={}", userUpdateParam.getNickName(), updateResult.getMessage());
        return updateResult;
    }

    @PostMapping("/user/update")
    @ApiOperation(value = "用户头像修改", notes = "需要token")
    public Result<String> updateAvatar(@RequestBody @Valid UserAvatarParam userAvatarParam, @TokenToUser Visitor visitor) throws IOException {
//        log.info(userAvatarParam.getAvatar());
//        log.info(userAvatarParam.getName());
        Result updateResult = vs.updateAvatar(userAvatarParam.getAvatar(),userAvatarParam.getName(), visitor.getUserId());
        logger.info("register api,nickName={},loginResult={}", visitor.getNickName(), updateResult.getMessage());
        return updateResult;
    }

//    @GetMapping(value = "/test1")
//    @ApiOperation(value = "测试接口", notes = "方法中含有TokenToUser注解")
//    public Result<String> test1(@TokenToUser Visitor visitor) {
//        Result result = null;
//        if (visitor == null) {
//            result = ResultGenerator.genErrorResult(416, "未登录！");
//            return result;
//        } else {
//            result = ResultGenerator.genSuccessResult("登录验证通过");
//        }
//        return result;
//    }
//
//    @GetMapping(value = "/test2")
//    @ApiOperation(value = "测试接口", notes = "方法中无@TokenToUser注解")
//    public Result<String> test2(){
//        Result result = ResultGenerator.genSuccessResult("此接口无须登录验证,请求成功");
//        return result;
//    }

    @GetMapping("/user/info")
    @ApiOperation(value = "获取用户信息", notes = "")
    public Result<Visitor> getUserDetail(@TokenToUser Visitor loginVisitor) {
        return ResultGenerator.genSuccessResult("返回登录用户信息",loginVisitor);
    }

    @PostMapping("/user/logout")
    @ApiOperation(value = "登出接口", notes = "清除token")
    public Result<String> logout(@TokenToUser Visitor loginVisitor) {
        Result logoutResult = vs.logout(loginVisitor.getUserId());
        logger.info("logout api,loginVisitor={}", loginVisitor.getUserId());
        return logoutResult;
    }

    @GetMapping("/user/vlist")
    @ApiOperation(value="用户列表", notes ="-_-")
    public ResultList getUserList() {
        return vs.getVisitorsList();
    }

}
