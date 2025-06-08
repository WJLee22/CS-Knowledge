package kr.ac.hansung.cse.hellospringboot;

import kr.ac.hansung.cse.hellospringboot.service.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

// 스프링부트 앱에서는 반드시 필요한 어노테이션(이 안에 @EnableAutoConfiguration, @ComponentScan 등 주요 애노테이션들이 포함되어 있다.)
// @ComponentScan는 이 @SpringBootApplication가 달린 클래스가 속한 패키지(hellospringboot)와 그 하위 패키지들(configuration, conroller, service)을 스캔하여 @Component, @Service, @Repository, @Controller 등의 어노테이션이 붙은 클래스를 찾아서 빈으로 등록한다.
// 이게 스프링부트의 auto-configuration 자동 설정 기능의 핵심이다. 기존에는 스캔대상 패키지들을 일일이 지정해주어야 했지만, 스프링부트에서는 @SpringBootApplication 어노테이션 하나로 자동으로 설정해준다.
@SpringBootApplication
@ComponentScan(basePackages = {"kr.ac.hansung.cse", "kr.ac.hansung.eecs"})
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
