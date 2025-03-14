package kr.ac.hansung.cse.animals;
//롬복으로 코드 간소화
import lombok.Setter;

public class Cat implements AnimalType {

    @Setter
    private String myName;

    @Override
    public void sound() {
        System.out.println("Cat name = " + myName + ":" + "Meow!");
    }

}
