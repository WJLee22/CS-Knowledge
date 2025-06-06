package kr.ac.hansung.cse.hellospringboot.configuration;

import kr.ac.hansung.cse.hellospringboot.service.MyService;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Java based configuration 방식으로 스프링 빈을 등록하는 방법을 보여준다.
@Configuration // 설정 클래스 안에서 @Bean 어노테이션을 사용하여 빈을 등록할 수 있다.
// application.properties 파일에서 설정 값을 읽어오기 위한 2번째 방법: @ConfigurationProperties 어노테이션을 사용한다.
@ConfigurationProperties(prefix = "app") // 이 어노테이션은 application.properties 파일에서 설정 값을 읽어올 때 사용된다. prefix는 설정 값의 접두사를 지정한다.
@Data
public class MyConfiguration {

    // prefix = "app"로 인해 app.professor, app.courseName 설정 값이 이 필드들에 착착 바인딩된다.
    private String professor;
    private String courseName;

    @Bean
    public MyService myService() {
        // @Bean 어노테이션이 붙은 메서드는 반환하는 객체를 스프링 컨테이너에 빈으로 등록한다.
        // MyService 객체를 생성하여 스프링 컨테이너에 등록한다.
        // 이 메서드는 MyService 타입의 빈을 생성하고, 이 빈은 스프링 컨테이너에서 관리된다.
        return new MyService();
    }
}
