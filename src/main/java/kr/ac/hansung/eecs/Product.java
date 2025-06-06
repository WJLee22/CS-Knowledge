package kr.ac.hansung.eecs;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component //자, 얘를 빈으로 등록하고자 @Component 어노테이션을 붙였지만 등록되지않는다.
// 왜 일까? 자, 지금 엔트리 포인트 클래스에 @SpringBootApplication 어노테이션이 붙어있다.
// 이 어노테이션은 @ComponentScan을 포함하고 있어서 해당 패키지와 그 하위 패키지를 스캔해서 @Component, @Repository 등 객체들을 빈으로 등록한다.
// 해당 패키지란 엔트리 포인트 클래스(main클래스)가 속한 패키지, 즉 kr.ac.hansung.hellospringboot 패키지이다.
// 따라서, kr.ac.hansung.eecs 패키지는 스캔 대상이 아니므로 빈으로 등록되지 않는다.
// 엔트리 포인트 클래스와 동일 패키지 및 하위 패키지에 있는 클래스들만 스캔 대상이 되는데 얜 kr.ac.hansung.eecs 패키지에 있으므로 스캔 대상이 아니기때문이다.

public class Product {
    private String name;
    private int price;
}
