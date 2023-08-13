package top.keng.anime.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.keng.anime.common.Constants;
import top.keng.anime.config.annotation.TokenToUser;
import top.keng.anime.model.Anime;
import top.keng.anime.model.Visitor;
import top.keng.anime.model.param.AnimePicInsertParam;
import top.keng.anime.model.param.AnimePicParam;
import top.keng.anime.service.AnimeService;
import top.keng.anime.utils.*;

import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@RestController
@Api(value = "anime", tags = "动漫操作接口")
public class AnimeController {

    @Autowired
    private AnimeService as;

    private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

    @PostMapping("/anime/insert")
    @ApiOperation(value = "动漫条目插入接口", notes = "")
    public Result insertAnime(@RequestBody @Valid Anime anime, @TokenToUser Visitor visitor) {
        Anime new_item = new Anime();
        BeanUtil.copyProperties(anime, new_item);
        new_item.setInsertUser(visitor.getUserId());
        new_item.setUpdateUser(visitor.getUserId());
        if (Objects.equals(anime.getP1(), ""))
            new_item.setP1(Constants.PICTURE_UPLOAD_DIC+"default1.png");
        else
            new_item.setP1(anime.getP1());
        if (Objects.equals(anime.getP2(), ""))
            new_item.setP2(Constants.PICTURE_UPLOAD_DIC+"default2.png");
        else
            new_item.setP2(anime.getP2());
        Result insertResult = as.insertAnime(new_item);
        logger.info("visitor {} insert item ,number={}, name={}", visitor.getUserId() ,new_item.getNumber(),new_item.getName());
        return insertResult;
    }
    @PostMapping("/anime/insertPic")
    @ApiOperation(value = "动漫信息更新接口", notes = "")
    public Result insertPic(@RequestBody @Valid AnimePicInsertParam animePicInsertParam, @TokenToUser Visitor visitor) {
        Result insertResult = as.insertPic(animePicInsertParam);
        logger.info("{} insert animePicture", visitor.getNickName());
        return insertResult;
    }
    @PostMapping("/anime/delete/{number}")
    @ApiOperation(value = "动漫条目删除接口", notes = "")
    public Result deleteAnime(@PathVariable("number") short number, @TokenToUser Visitor visitor) {
        Result deleteResult = as.deleteAnime(number);
        logger.info("{} delete item ,number={}", visitor.getNickName(), number);
        return deleteResult;
    }

    @PostMapping("/anime/update")
    @ApiOperation(value = "动漫信息更新接口", notes = "")
    public Result updateAnime(@RequestBody @Valid Anime anime, @TokenToUser Visitor visitor) {
        anime.setUpdateUser(visitor.getUserId());
        Result updateResult = as.updateAnime(anime);
        logger.info("{} update item ,number={}, name={}", visitor.getNickName(), anime.getNumber(), anime.getName());
        return updateResult;
    }

    @PostMapping("/anime/updatePic")
    @ApiOperation(value = "动漫信息更新接口", notes = "")
    public Result updateAnimePic(@RequestBody @Valid AnimePicParam animePicParam, @TokenToUser Visitor visitor) {
        Result updateResult = as.updateAnimePic(animePicParam, visitor.getUserId());
        logger.info("{} update animePicture ,number={}", visitor.getNickName(), animePicParam.getNumber());
        return updateResult;
    }

    @GetMapping("/anime/select/{number}")
    @ApiOperation(value = "接口", notes = "")
    public Result selectAnime(@PathVariable("number") Short number, @TokenToUser Visitor visitor) {
        Result selectResult = as.selectAnime(number);
        logger.info("{} find anime , number={}", visitor.getNickName(), number);
        return selectResult;
    }

    @GetMapping("/anime/selectfv/{number}")
    @ApiOperation(value = "接口", notes = "")
    public Result selectAnimefv(@PathVariable("number") Short number) {
        Result selectResult = as.selectAnime(number);
        logger.info("{} find anime , number={}", "visitor", number);
        return selectResult;
    }

    @GetMapping("/anime/all")
    @ApiOperation(value = "动漫列表", notes = "")
    public ResultList findAllAnime() {
        return as.allAnime();
    }

    @GetMapping("/anime/search/{query}")
    @ApiOperation(value = "动漫列表", notes = "")
    public ResultList searchAnime(@PathVariable("query") String query,@TokenToUser Visitor visitor) {
        ResultList searchList = as.searchAnime(query);
        logger.info("{} wants to search {} ", visitor.getNickName(), query);
        return searchList;
    }

    @GetMapping("/anime/searchfv/{query}")
    @ApiOperation(value = "动漫列表", notes = "")
    public ResultList searchAnimefv(@PathVariable("query") String query) {
        ResultList searchList = as.searchAnime(query);
        logger.info("{} wants to search {} ", "visitor" , query);
        return searchList;
    }
}
