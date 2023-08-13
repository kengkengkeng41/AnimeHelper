package top.keng.anime.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.keng.anime.config.annotation.TokenToUser;
import top.keng.anime.model.Links;
import top.keng.anime.model.Visitor;
import top.keng.anime.service.LinksService;
import top.keng.anime.utils.Result;
import top.keng.anime.utils.ResultList;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(value = "links", tags = "链接操作接口")
public class LinksController {
    @Autowired
    private LinksService ls;
    private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

    @PostMapping("/links/insert")
    @ApiOperation(value = "链接插入", notes = "需要token")
    public Result insertLinks(@RequestBody @Valid Links links, @TokenToUser Visitor visitor) {
        Result insertResult = ls.insertLinks(links, visitor.getUserId());
        logger.info("links insert api,nickName={},insertResult={}", visitor.getNickName(), insertResult.getMessage());
        return insertResult;
    }

    @GetMapping("/links/select/{number}")
    @ApiOperation(value="获取链接列表", notes ="#_#")
    public ResultList getLinksList(@PathVariable("number") short number) {
        return ls.selectLinks(number);
    }

    @GetMapping("/links/selectAll")
    @ApiOperation(value="获取所有链接", notes ="&_&")
    public ResultList getAll() {
        return ls.selectAll();
    }

    @PostMapping("/links/delete/{links}")
    @ApiOperation(value = "删除链接", notes = "需要token")
    public Result deleteLinks(@PathVariable("links") String name, @TokenToUser Visitor visitor) {
        Result deleteResult = ls.deleteLinks(name);
        logger.info("links delete api,nickName={},deleteResult={}", visitor.getNickName(), deleteResult.getMessage());
        return deleteResult;
    }

}
