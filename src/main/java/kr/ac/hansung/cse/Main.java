package kr.ac.hansung.cse;

import kr.ac.hansung.cse.animals.PetOwner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 1. Spring Container 생성하기 (ClassPathXmlApplicationContext클래스의 인스턴스)
        // 인자값으로 스프링 컨테이너의 동작을 정의하는 xml 파일 넘겨줌.
        // 이 스프링 컨테이너가 생성됨과 같이, xml파일에 정의된 bean들이 스프링 컨테이너에 의해 스프링 컨테이너 내부에 생성됨.
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("conf/animal.xml");

        // 2. Spring Container에 생성된 bean 가져오기.
        // getBean 메서드의 인자값으로, 참조하고자 하는 빈의 bean id를 넘겨줌.
        // 이때, getBean의 반환값은 Object타입이므로, 실제 타입으로 형변환 해줘야함.
        // Spring Container안에 있는 petOwnerId라는 id를 가진 bean을 가져옴
        PetOwner person = (PetOwner)context.getBean("petOwnerId");

        // 3. 가져온 bean을 사용하기
        // 해당 PetOwner 타입 객체의 animal 필드에 주입된 bean에 대해 sound() 메서드를 호출함.
        person.play();

        // 4. Spring Container 종료하기
        // Spring Container를 종료하기 위해서는 close() 메서드를 호출해야함.
        // close() 메서드를 호출하지 않으면, JVM이 종료되지 않음.
        // JVM이 종료되지 않으면, 프로그램이 종료되지 않음.
        // 따라서, close() 메서드를 호출하여 Spring Container를 종료해야함.
        context.close();
    }

}