package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Animal{
    private final String name;
    private final Integer age;

    Animal(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}

class Dog extends Animal{
    Dog(String name, Integer age){
        super(name, age);
    }

    @Override
    public String toString() {
        return "Dog name = " + getName() + ", age = " + getAge();
    }
}

class Cat extends Animal{
    Cat(String name, Integer age){
        super(name, age);
    }

    @Override
    public String toString() {
        return "Cat name = " + getName() + ", age = " + getAge();
    }
}


interface BaseIterator<T>{
    T next();
    boolean hasNext();
    void reset();
}

class AnimalIterator implements BaseIterator<Animal>{
    private List<Animal> animals;
    private Integer currentIndex;

    public AnimalIterator(List<Animal> animals){
        this.animals = animals;
        this.currentIndex = 0;
    }

    @Override
    public Animal next() {
        Animal currentAnimal = animals.get(currentIndex);
        currentIndex++;
        return currentAnimal;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < animals.size();
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = getValidNumber(scanner);
        List<Animal> pets = new ArrayList<>(count);

        for (int i = 0; i < count; i++){

            String petType = scanner.nextLine().toLowerCase().trim();
            if (!petType.equals("dog") && !petType.equals("cat")){
                System.out.println("Incorrect input. Unsupported pet type");
                continue;
            }

            String name = scanner.nextLine().trim();

            int age = getValidAge(scanner);
            if (age <= 0){
                System.out.println("Incorrect input. Age <= 0");
                continue;
            }


            if (petType.equals("dog")){
                pets.add(new Dog(name,age));
            } else pets.add(new Cat(name, age));
        }

        AnimalIterator iterator = new AnimalIterator(pets);
        while (iterator.hasNext()){
            Animal pet = iterator.next();
            System.out.println(pet);
        }

    }

    private static int getValidNumber(Scanner scanner){
        while (true){
            String count = scanner.nextLine().trim();
            try {
                return Integer.parseInt(count);
            } catch (NumberFormatException e){
                System.out.println("Could not parse a number. Please, try again");
            }
        }
    }

    private static int getValidAge(Scanner scanner){
        while (true){
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.out.println("Could not parse a number. Please, try again");
            }
        }
    }

}

