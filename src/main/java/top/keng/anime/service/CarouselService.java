package top.keng.anime.service;

import top.keng.anime.model.Carousel;
import top.keng.anime.utils.Result;
import top.keng.anime.utils.ResultList;

public interface CarouselService {
    Result insertCarousel(Carousel carousel, Long userId);
    Result deleteCarousel(Byte id);
    ResultList findCarousel();

}
