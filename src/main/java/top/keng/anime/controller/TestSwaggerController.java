package top.keng.anime.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import top.keng.anime.model.Visitor;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TestSwaggerController {
    static Map<Integer, Visitor> vMap= Collections.synchronizedMap(new HashMap<Integer, Visitor>());

    static {
        Visitor v1 = new Visitor();
        v1.setUserId( (long) 101 );
        v1.setRole("0");
        v1.setLoginName("sunjia1266");
        v1.setNickName("孙家坑");
        v1.setPasswordMd5("123456");

        Visitor v2 = new Visitor();
        v2.setUserId( (long) 102 );
        v2.setRole("0");
        v2.setLoginName("sunjia1267");
        v2.setNickName("孙家坑2");
        v2.setPasswordMd5("123456");

        vMap.put(1,v1);
        vMap.put(2,v2);
    }

    @ApiOperation(value = "获取测试用户列表", notes="")
    @GetMapping("/visitors_test")
    public List<Visitor> getVisitorList() {
        List<Visitor> visitors = new ArrayList<Visitor>(vMap.values());
        return visitors;
    }
}
