package org.example;

import java.util.*;
import java.util.concurrent.*;

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

    public abstract double goToWalk();
}

class Dog extends Animal{
    Dog(String name, Integer age){
        super(name, age);
    }

    @Override
    public String toString() {
        return "Dog name = " + getName() + ", age = " + getAge();
    }

    @Override
    public double goToWalk() {
        double walkTime = getAge() * 0.5;
        try {
            TimeUnit.SECONDS.sleep((long)walkTime);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        return walkTime;
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

    @Override
    public double goToWalk() {
        double walkTime = getAge() * 0.25;
        try {
            TimeUnit.SECONDS.sleep((long)walkTime);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        return walkTime;
    }
}


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Animal> pets = new ArrayList<>();

        int numberOfPets = getValidNumber(scanner);

        for (int i = 0; i < numberOfPets; i++){
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

            Animal pet;
            if (petType.equals("dog")){
                pet = new Dog(name, age);
            } else {
                pet = new Cat(name, age);
            }
            pets.add(pet);
        }
        long programStartTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(pets.size());
        List<Future<Void>> futures = new ArrayList<>();

        for (Animal pet : pets ) {
            Future<Void> future = executorService.submit( () -> {
                long walkStartTime = System.currentTimeMillis();
                double walkDuration = pet.goToWalk();
                long walkEndTime = System.currentTimeMillis();

                double startTimeFromProgramStart = (walkStartTime - programStartTime) / 1000.0;
                double endTimeFromProgramStart = (walkEndTime - programStartTime) / 1000.0;

                synchronized (System.out){
                    System.out.printf("%s, start time: %.2f , end time: %.2f %n",
                            pet.toString(), startTimeFromProgramStart, endTimeFromProgramStart);
                }
                return null;
            } );
            futures.add(future);
        }

        for (Future<Void> future : futures){
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e){
                e.fillInStackTrace();
            }
        }
        executorService.shutdown();
        scanner.close();

    }

    private static int getValidNumber(Scanner scanner){
        while (true){
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
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

