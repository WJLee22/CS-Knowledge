package kr.ac.hansung.cse.animals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/*
@AllArgsConstructor //모든 필드들을 매개변수로하는 생성자를 생성하는 롬복 어노테이션.
*/
public class PetOwner {
    @Autowired // @Autowired 어노테이션을 사용하여, 스프링 컨테이너에 의해 주입될 bean을 지정함.(주입될 빈은, 컨테이너 내부의 해당 필드의 클래스 타입(AnimalType)과 동일한 빈이 선택되어 주입됨)
    @Qualifier(value = "qf_dog") // @Qualifier 어노테이션을 사용하여, 주입할 bean 내부에 기술해둔 qualifier 태그의 value 속성값을 명시하여 Autowired의 동타입 선택문제 해결.
    //단, Autowired는 type 기반 주입(wired by type)이어서, 현재 AnimalType타입 객체는 cat & dog 2개가 존재하므로, QualiFier를 사용하여 어떤 bean을 주입할지 명시해야함.
    public AnimalType animal; // Dependency Injection: XML config 코드에 따라 Cat or Dog bean 주입 예정

    public void play(){
        animal.sound();
    }
}

