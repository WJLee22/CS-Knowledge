package kr.ac.hansung.cse.hellospringboot;

import kr.ac.hansung.cse.hellospringboot.service.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

// 스프링부트 앱에서는 반드시 필요한 어노테이션(이 안에 @EnableAutoConfiguration, @ComponentScan 등 주요 애노테이션들이 포함되어 있다.)
@SpringBootApplication
public class HelloSpringBootApplication {

    // 전통 스프링mvc에서는 main메서드가 보이지 않았지만, 스프링부트에서는 main메서드가 보인다.
    // main메서드는 스프링부트 애플리케이션을 실행하는 진입점이다.
    // 이때 jar 파일로 패키징되어(톰켓서버랑 같이) jar파일이 만들어지고 이 jar파일이 실행되면 스프링부트 애플리케이션이 시작된다.
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HelloSpringBootApplication.class, args);

        // 메서드의 이름이 빈의 id.
        MyService myService = (MyService) context.getBean("myService");
        System.out.println(myService.getMessage());

    }
}
