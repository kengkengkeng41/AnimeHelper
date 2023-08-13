package top.keng.anime.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.keng.anime.mapper.CarouselMapper;
import top.keng.anime.model.Carousel;
import top.keng.anime.service.CarouselService;
import top.keng.anime.utils.*;

import java.util.List;

@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper cm;
    @Override
    public Result insertCarousel(Carousel carousel, Long userId) {
        Carousel c = new Carousel();
        BeanUtil.copyProperties(carousel,c);
        c.setInsertUser(userId);
        if (cm.insertSelective(c) > 0) {
            return ResultGenerator.genSuccessResult("轮播图插入成功");
        }
        return ResultGenerator.genErrorResult(603,"轮播图插入末端");
    }
    @Override
    public Result deleteCarousel(Byte id) {
        if (cm.deleteByPrimaryKey(id) > 0) {
            String message = "成功删除轮播图："+id;
            return ResultGenerator.genSuccessResult(message);
        }
        return ResultGenerator.genErrorResult(603,"轮播图删除末端");
    }

    @Override
    public ResultList findCarousel() {
        List<Carousel> carouselList = cm.selectAll();
        return ResultListGenerator.genSuccessResult("轮播图数："+carouselList.size(), carouselList);
    }
}
