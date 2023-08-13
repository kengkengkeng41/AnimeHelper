package top.keng.anime.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.keng.anime.common.ServiceResultEnum;
import top.keng.anime.mapper.AnimeMapper;
import top.keng.anime.model.Anime;
import top.keng.anime.model.param.AnimePicInsertParam;
import top.keng.anime.model.param.AnimePicParam;
import top.keng.anime.service.AnimeService;
import top.keng.anime.utils.*;

import java.util.List;

@Service
@Slf4j
public class AnimeServiceImpl implements AnimeService {
    @Autowired
    private AnimeMapper am;
    @Override
    public Result insertAnime(Anime anime){
        String message = "成功添加动漫条目：#"+anime.getNumber()+" "+anime.getName();
        if (am.insertSelective(anime) > 0) return ResultGenerator.genSuccessResult(message);
        return ResultGenerator.genErrorResult(603,"动漫添加末端");
    }

    public Result insertPic(AnimePicInsertParam apip) {
        String url = UploadUtil.pictureUpload(UploadUtil.base64ToMultipart(apip.getPicture()), "_", apip.getFilename());
        return ResultGenerator.genSuccessResult("新建时插入图片", url);
    }
    @Override
    public Result deleteAnime(Short number){
        if (am.deleteByPrimaryKey(number) > 0) {
            String message = "成功删除动漫条目：#"+number;
            return ResultGenerator.genSuccessResult(message);
        }
        return ResultGenerator.genErrorResult(603,"动漫删除末端");
    }
    @Override
    public Result updateAnime(Anime anime){
        if (am.updateByPrimaryKeySelective(anime) > 0) {
            return ResultGenerator.genSuccessResult("成功更新动漫条目：#"+anime.getName()+" "+anime.getName());
        }
        return ResultGenerator.genErrorResult(603,"动漫修改末端");
    }

    @Override
    public Result updateAnimePic(AnimePicParam app, Long userId){
        Anime anime = am.selectByPrimaryKey(app.getNumber());
        String url = UploadUtil.pictureUpload(UploadUtil.base64ToMultipart(app.getPicture()), anime.getNumber().toString(), app.getFilename());
//        if (app.getType().equals("1")) {
//            anime.setP1(url);
//            anime.setUpdateUser(userId);
//            am.updateByPrimaryKeySelective(anime);
//            return ResultGenerator.genSuccessResult("#"+userId+"成功修改p1，"+app.getNumber(),anime);
//        }
//        else if (app.getType().equals("2")) {
//            anime.setP2(url);
//            anime.setUpdateUser(userId);
//            am.updateByPrimaryKeySelective(anime);
//            return ResultGenerator.genSuccessResult("#"+userId+"成功修改p2，"+app.getNumber(),anime);
//        }
//        return ResultGenerator.genErrorResult(603,"动漫修改末端");
        return ResultGenerator.genSuccessResult("更新#"+anime.getNumber()+"图片", url);
    }
    @Override
    public Result selectAnime(Short number){
        Anime anime = am.selectByPrimaryKey(number);
        if (anime == null) return ResultGenerator.genErrorResult(601, "当前番剧不存在");
        return ResultGenerator.genSuccessResult("查询成功", anime);
    }
    @Override
    public ResultList allAnime(){
        List<Anime> animeList = am.selectAllAnime();
        return ResultListGenerator.genSuccessResult("当前动漫数："+animeList.size(), animeList);
    }
    @Override
    public ResultList searchAnime(String query){
        List<Anime> al = am.selectByName(query);
        if (!al.isEmpty()) return ResultListGenerator.genSuccessResult("按名查找动漫成功",al);
        return ResultListGenerator.genErrorResult(603,"按名查找末端");
    }
}
