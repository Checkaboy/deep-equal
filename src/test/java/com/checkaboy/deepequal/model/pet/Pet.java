package com.checkaboy.deepequal.model.pet;

/**
 * @author Taras Shaptala
 */
public class Pet {

    private String nickname;
    private int age;
    private EAnimal animal;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public EAnimal getAnimal() {
        return animal;
    }

    public void setAnimal(EAnimal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nickname='" + nickname + '\'' +
                ", age=" + age +
                ", animal=" + animal +
                '}';
    }

}
