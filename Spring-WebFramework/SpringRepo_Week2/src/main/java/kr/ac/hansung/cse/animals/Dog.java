package kr.ac.hansung.cse.animals;

import lombok.Setter;

public class Dog implements AnimalType{
    @Setter
    private String myName;

    @Override
    public void sound() {
        System.out.println("Dog name = " + myName + ":" + "Bow Wow!");
    }

}
