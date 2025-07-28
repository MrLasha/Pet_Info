package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Animal {
    private final String name;
    private final Integer age;
    private final Double mass;

    Animal(String name, Integer age, Double mass){
        this.name = name;
        this.age = age;
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getMass() {
        return mass;
    }

    public abstract Double getFeedInfoKg();

}

class Dog extends Animal{
    Dog(String name, Integer age, Double mass){
        super(name, age, mass);
    }

    @Override
    public Double getFeedInfoKg() {
        return this.getMass() * 0.3 ;
    }

    @Override
    public String toString() {
        return "Dog name = " + this.getName()
                + " , age = " + this.getAge()
                + " , mass = " + String.format("%.2f", this.getMass())
                + " , feed = " + String.format("%.2f", this.getFeedInfoKg());
    }
}

class Cat extends Animal{
    Cat(String name, Integer age, Double mass){
        super(name, age, mass);
    }

    @Override
    public Double getFeedInfoKg() {
        return this.getMass() * 0.1;
    }

    @Override
    public String toString() {
        return "Cat name = " + this.getName()
                + " , age = " + this.getAge()
                + " , mass = " + String.format("%.2f", this.getMass())
                + " , feed = " + String.format("%.2f", this.getFeedInfoKg());
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

        animalIteration : for (int i = 0; i < count; i++){
            String type = scanner.nextLine().trim().toLowerCase();
            if (!type.equals("dog") && !type.equals("cat")){
                System.out.println("Incorrect input. Unsupported pet type");
                continue;
            }

            String name = scanner.nextLine().trim();

            String petAge = scanner.nextLine();
            Integer age = null;
            while (age == null){
            try{
                age = Integer.parseInt(petAge);
                if (age <= 0){
                    System.out.println("Incorrect input. Age <= 0");
                    continue animalIteration;
                }
            } catch (NumberFormatException e){
                System.out.println("Could not parse a number. Please, try again");
            }
            }


            String petMass = scanner.nextLine();
            Double mass = null;
            while(mass == null){
            try {
                mass = Double.parseDouble(petMass);
                if (mass <= 0){
                    System.out.println("Incorrect input. Mass <= 0");
                    continue animalIteration;
                }
            } catch (NumberFormatException e){
                System.out.println("Could not parse a number. Please, try again");
            }
            }


            if (type.equals("dog")){
                pets.add(new Dog(name, age, mass));
            } else pets.add(new Cat(name, age, mass));
        }

        for (Animal pet : pets){
            System.out.println(pet);
        }
    }
}