package top.keng.anime.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.keng.anime.config.annotation.TokenToUser;
import top.keng.anime.model.Carousel;
import top.keng.anime.model.Visitor;
import top.keng.anime.service.CarouselService;
import top.keng.anime.utils.Result;
import top.keng.anime.utils.ResultList;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(value = "carousel", tags = "轮播图操作接口")
public class CarouselController {
    @Autowired
    private CarouselService cs;
    private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

    @PostMapping("/carousel/insert")
    @ApiOperation(value = "轮播图插入", notes = "需要token")
    public Result insertCarousel(@RequestBody @Valid Carousel links, @TokenToUser Visitor visitor) {
        Result insertResult = cs.insertCarousel(links, visitor.getUserId());
        logger.info("carousel insert api,nickName={},insertResult={}", visitor.getNickName(), insertResult.getMessage());
        return insertResult;
    }

    @GetMapping("/carousel/select")
    @ApiOperation(value="获取轮播图列表", notes ="#_#")
    public ResultList getCarouselList() {
        return cs.findCarousel();
    }

    @PostMapping("/carousel/delete/{id}")
    @ApiOperation(value = "删除轮播图", notes = "需要token")
    public Result deleteCarousel(@PathVariable("id") Byte id, @TokenToUser Visitor visitor) {
        Result deleteResult = cs.deleteCarousel(id);
        logger.info("carousel delete api,nickName={},deleteResult={}", visitor.getNickName(), deleteResult.getMessage());
        return deleteResult;
    }

}
