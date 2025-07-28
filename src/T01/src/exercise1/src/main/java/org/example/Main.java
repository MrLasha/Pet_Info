package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Animal{
    private final String name;
    private final Integer age;
    public Animal(String name, Integer age){
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return name;
    }
    public Integer getAge(){
        return age;
    }
}

class Dog extends Animal{
    Dog(String name, Integer age){
        super(name, age);
    }

    @Override
    public String toString() {
        return "Dog name = " + this.getName() + ", age = " + this.getAge();
    }
}

class Cat extends Animal{

    Cat(String name, Integer age){
        super(name,age);
    }

    @Override
    public String toString() {
        return "Cat name = " + this.getName() + ", age = " + this.getAge();
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer count = null;
        while (count == null) {
            System.out.println("Enter the number of animals");
            String input = scanner.nextLine();
            try {
                count = Integer.parseInt(input);
                if (count < 0) {
                    System.out.println("Number cannot be negative. Try again");
                    count = null;
                } else if (count == 0) {
                    System.out.println("Number should be grater then 0. Try again");
                    count = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }

        List<Animal> pets = new ArrayList<>(count);

        nextAnimal : for (int i = 0; i < count; i++) {
            String type = scanner.nextLine().trim().toLowerCase();
            if (!type.equals("dog") && !type.equals("cat")) {
                System.out.println("Incorrect input. Unsupported pet type");
                continue;
            }

            String name = scanner.nextLine().trim();

            Integer age = null;
            while (age == null) {
                String ageInput = scanner.nextLine().trim();
                try {
                    age = Integer.parseInt(ageInput);
                    if (age <= 0) {
                        System.out.println("Incorrect input. Age <= 0");
                        continue nextAnimal;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Could not parse a number. Please, try again");
                }
            }

            if (type.equals("dog")) {
                pets.add(new Dog(name, age));
            } else {
                pets.add(new Cat(name, age));
            }
        }
        for (Animal pet : pets){
            System.out.println(pet);
        }
    }
}