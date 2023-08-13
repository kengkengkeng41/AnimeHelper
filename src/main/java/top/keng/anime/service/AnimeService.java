package top.keng.anime.service;

import top.keng.anime.model.Anime;
import top.keng.anime.model.param.AnimePicInsertParam;
import top.keng.anime.model.param.AnimePicParam;
import top.keng.anime.utils.Result;
import top.keng.anime.utils.ResultList;

public interface AnimeService {
    Result insertAnime(Anime anime);
    Result insertPic(AnimePicInsertParam apip);
    Result deleteAnime(Short number);
    Result updateAnime(Anime anime);
    Result updateAnimePic(AnimePicParam app, Long userId);
    Result selectAnime(Short number);
    ResultList allAnime();
    ResultList searchAnime(String query);
}
