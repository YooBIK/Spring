package hello.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setAge(10);
        helloLombok.setName("asdf");
        String name = helloLombok.getName();
        int age = helloLombok.getAge();
        System.out.println("age = " + age);
        System.out.println("name = " + name);
    }

}
