package kr.ac.hansung.cse.animals;

import lombok.AllArgsConstructor;

@AllArgsConstructor //모든 필드들을 매개변수로하는 생성자를 생성하는 롬복 어노테이션.
public class PetOwner {
    public AnimalType animal; // Dependency Injection: XML config 코드에 따라 Cat or Dog bean 주입 예정

    public void play(){
        animal.sound();
    }
}
