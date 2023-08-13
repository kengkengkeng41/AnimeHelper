package top.keng.anime.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.keng.anime.mapper.LinksMapper;
import top.keng.anime.model.Links;
import top.keng.anime.service.LinksService;
import top.keng.anime.utils.*;

import java.util.List;

@Service
@Slf4j
public class LinksServiceImpl implements LinksService {
    @Autowired
    private LinksMapper lm;
    @Override
    public Result insertLinks(Links links, Long userId) {
        Links ls = new Links();
        BeanUtil.copyProperties(links,ls);
        ls.setInsertUser(userId);
        if (lm.insertSelective(ls) > 0) {
            return ResultGenerator.genSuccessResult("链接插入成功");
        }
        return ResultGenerator.genErrorResult(603,"链接插入末端");
    }
    @Override
    public Result deleteLinks(String linkName) {
        if (lm.deleteByPrimaryKey(linkName) > 0) {
            String message = "成功删除链接："+linkName;
            return ResultGenerator.genSuccessResult(message);
        }
        return ResultGenerator.genErrorResult(603,"链接删除末端");
    }

    @Override
    public ResultList selectLinks(short animeNumber) {
        List<Links> linkList = lm.selectByAnime(animeNumber);
        return ResultListGenerator.genSuccessResult("动漫#"+animeNumber+"链接数："+linkList.size(), linkList);
    }

    @Override
    public ResultList selectAll() {
        List<Links> linkList = lm.selectAll();
        return ResultListGenerator.genSuccessResult("链接数量："+linkList.size(), linkList);
    }
}
