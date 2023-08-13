package top.keng.anime.service;

import org.springframework.web.multipart.MultipartFile;
import top.keng.anime.model.param.UserUpdateParam;
import top.keng.anime.utils.*;

import java.io.IOException;

public interface VisitorService {

    Result register(String loginName, String password);
    Result login(String loginName, String passwordMD5);
    Result updateVisitorInfo(UserUpdateParam user, Long userId);
    Result updateAvatar(String avatar,String name, Long userId) throws IOException;
    Result logout(Long userId);
    ResultList getVisitorsList();

}
