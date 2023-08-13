package top.keng.anime.service;

import top.keng.anime.model.Links;
import top.keng.anime.utils.Result;
import top.keng.anime.utils.ResultList;

public interface LinksService {
    Result insertLinks(Links links, Long userId);
    Result deleteLinks(String linkName);
    ResultList selectLinks(short animeNumber);
    ResultList selectAll();

}
