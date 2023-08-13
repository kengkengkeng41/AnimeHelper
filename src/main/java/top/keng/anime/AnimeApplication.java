package top.keng.anime;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.keng.anime.common.Constants;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
public class AnimeApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(AnimeApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		generateArtifacts();
//		playWithArtifacts();
		log.info("ending........");
//		String test = "http://localhost:8081/avatar/testrejficme_rj3562.png";
//		int pos = test.lastIndexOf("/");
//		log.info(test.substring(pos+1));
	}

	private void generateArtifacts() throws Exception {
		List<String> warnings = new ArrayList<>();
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(
				this.getClass().getResourceAsStream("/generatorConfig.xml"));
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		log.info("sssssssssssssssssssssssssss");
		myBatisGenerator.generate(null);
		log.info("done");

	}

//	private void playWithArtifacts() {
//		CurUser user1 = new CurUser()
//				.withUsername("admin_wdnmd")
//				.withPasswd("root123456")
//				.withCreatetime(new Date())
//				.withUpdatetime(new Date());
//
//		cum.insert(user1);
////
//		CurUser user2 = new CurUser()
//				.withUsername("admin_caole")
//				.withPasswd("root123456")
//				.withCreatetime(new Date())
//				.withUpdatetime(new Date());;
//
//		cum.insert(user2);

//		ChatUser s = cum.selectByPrimaryKey(7L);
//		log.info("User7 {}", s);
//		List<CurUser> list = cum.selectAll();
//		list.forEach(e -> log.info("selectByExample: {}", e));

//		ChatUserExample example = new ChatUserExample();
//		example.createCriteria().andUsernameEqualTo("caole");
//		List<ChatUser> list = cum.selectByExample(example);
//		list.forEach(e -> log.info("selectByExample: {}", e));
//	}
}
