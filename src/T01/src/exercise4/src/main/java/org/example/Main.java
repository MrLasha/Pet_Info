package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

abstract class Animal{
    private final String name;
    private final Integer age;

    Animal(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public abstract Animal withIncreasedAge();
}

class Dog extends Animal{
    Dog(String name, Integer age){
        super(name, age);
    }

    @Override
    public String toString() {
        return "Dog name = " + this.getName() + ", age = " + this.getAge();
    }

    @Override
    public Animal withIncreasedAge() {
        return new Dog(this.getName(), this.getAge() + 1);
    }
}

class Cat extends Animal{
    Cat(String name, Integer age){
        super(name, age);
    }

    @Override
    public String toString() {
        return "Cat name = " + this.getName() + ", age = " + this.getAge();
    }

    @Override
    public Animal withIncreasedAge() {
        return new Cat(this.getName(), this.getAge() + 1);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = getAnimalCount(scanner);

        List <Animal> pets = new ArrayList<>(count);

        IntStream.range(0, count)
                .forEach((i) -> {
                    Animal pet = readAnimal(scanner);
                    if(pet != null){
                        pets.add(pet);
                    }
                });

        List<Animal> updatedPets = pets.stream()
                .map(animal -> animal.getAge() > 10 ? animal.withIncreasedAge() : animal)
                .toList();

        updatedPets.forEach(System.out::println);

    }

    private static Animal readAnimal(Scanner scanner){
                String type = scanner.nextLine().toLowerCase().trim();
        if (!type.equals("dog") && !type.equals("cat")){
            System.out.println("Incorrect input. Unsupported pet type");
            return null;
        }

        String name = scanner.nextLine().trim();

        String scanAge = scanner.nextLine();
        int age;
        try{
            age = Integer.parseInt(scanAge);
            if (age <= 0){
                System.out.println("Incorrect input. Age <= 0");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Could not parse a number. Please, try again");
            return null;
        }
        if (type.equals("dog")){
            return new Dog(name,age);
        } else{
            return new Cat(name,age);
        }
    }

    public static int getAnimalCount(Scanner scanner){
        System.out.println("Enter the number of animals");
        String input = scanner.nextLine();
        try {
            int count = Integer.parseInt(input);
            if (count <= 0){
                System.out.println(count < 0 ?
                        "Number cannot be negative. Try again" :
                        "Number should be grater then 0. Try again" );
                return getAnimalCount(scanner);
            }
            return count;
        } catch (NumberFormatException e){
            System.out.println("Could not parse a number. Please, try again");
            return getAnimalCount(scanner);
        }
    }
}

