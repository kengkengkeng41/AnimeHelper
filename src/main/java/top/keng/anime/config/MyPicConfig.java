package top.keng.anime.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.keng.anime.common.Constants;

@Configuration
public class MyPicConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //加入后现有路径失效
//        registry.addResourceHandler("/avatar/**").addResourceLocations("classpath:/avatar/");
    }
}
