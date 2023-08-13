package top.keng.anime.config.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.keng.anime.common.AnimeException;
import top.keng.anime.common.Constants;
import top.keng.anime.common.ServiceResultEnum;
import top.keng.anime.config.annotation.TokenToUser;
import top.keng.anime.mapper.UserTokenMapper;
import top.keng.anime.mapper.VisitorMapper;
import top.keng.anime.model.UserToken;
import top.keng.anime.model.Visitor;

@Component
public class TokenToUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private VisitorMapper vm;
    @Autowired
    private UserTokenMapper userTokenMapper;

    public TokenToUserMethodArgumentResolver() {

    }


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterAnnotation(TokenToUser.class) != null) {
            Visitor visitor = null;
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == Constants.TOKEN_LENGTH) {
                UserToken userToken = userTokenMapper.selectByToken(token);
                if (userToken == null || userToken.getExpireTime().getTime() <= System.currentTimeMillis()) {
                    AnimeException.fail(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult());
                }
                visitor = vm.selectByPrimaryKey(userToken.getUserId());
                if (visitor == null) {
                    AnimeException.fail(ServiceResultEnum.USER_NULL_ERROR.getResult());
                }

//                if (visitor.getLockedFlag().intValue() == 1) {
//                    AnimeException.fail(ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult());
//                }

                return visitor;
            } else {
                AnimeException.fail(ServiceResultEnum.NOT_LOGIN_ERROR.getResult());
            }
        }
        return null;
    }
}
