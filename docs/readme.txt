template engine
timing task

passport
dashboard


https://fancy.com/hongquanli
infinite
thymeleaf

System.setProperty("spring.profiles.active", "production");

MQTT

Kotlin in Action
Kotlin for Android Developers
https://light.hs.net/portal/not-support.html


lightart/github

encyclopedia


快速清空文件内容：
　　$ : > filename #其中的 : 是一个占位符, 不产生任何输出.
　　$ > filename
　　$ echo '' > filename
　　$ echo /dev/null > filename
　　$ echo > filename
　　$ cat /dev/null > filename



Spring boot项目打成war包部署到tomcat
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Starter.class);
    }
}

OR

@SpringBootApplication
@ComponentScan
@Import({DBConfiguration.class, ResourceConfiguration.class,AppConfiguration.class})
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
