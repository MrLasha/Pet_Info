package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;


interface Herbivore {
    String chill();
}
interface Omnivore {
    String hunt();
}

abstract class Animal {
    private final String name;
    private final Integer age;

    Animal(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}

class Dog extends Animal implements Omnivore {
    Dog(String name, Integer age) {
        super(name, age);
    }

    @Override
    public String hunt() {
        return "I can hunt for robbers";
    }

    @Override
    public String toString() {
        return "Dog name = " + this.getName()
                + ", age = " + this.getAge() + ". "
                + hunt();
    }
}

class Cat extends Animal implements Omnivore {
    Cat(String name, Integer age) {
        super(name, age);
    }

    @Override
    public String hunt() {
        return "I can hunt for mice";
    }

    public String toString() {
        return "Cat name = " + this.getName()
                + ", age = " + this.getAge() + ". "
                + hunt();
    }

}

class Hamster extends Animal implements Herbivore {
    Hamster(String name, Integer age) {
        super(name, age);
    }

    @Override
    public String chill() {
        return "I can chill for 8 hours";
    }

    @Override
    public String toString() {
        return "Hamster name = " + this.getName()
                + " , age = " + this.getAge() + ". "
                + chill();
    }
}

class GuineaPig extends Animal implements Herbivore {
    GuineaPig(String name, Integer age) {
        super(name, age);
    }

    @Override
    public String chill() {
        return "I can chill for 12 hours";
    }

    @Override
    public String toString() {
        return "GuineaPig name = " + this.getName()
                + " , age = " + this.getAge() + ". "
                + chill();
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

        animalIteration:
        for (int i = 0; i < count; i++) {
            String type = scanner.nextLine().trim().toLowerCase();
            if (!type.equals("dog") && !type.equals("cat") && !type.equals("hamster") && !type.equals("guinea")) {
                System.out.println("Incorrect input. Unsupported pet type");
                continue;
            }

            String name = scanner.nextLine().trim();

            String petAge = scanner.nextLine();
            Integer age = null;
            while (age == null) {
                try {
                    age = Integer.parseInt(petAge);
                    if (age <= 0) {
                        System.out.println("Incorrect input. Age <= 0");
                        continue animalIteration;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Could not parse a number. Please, try again");
                }
            }

            switch (type) {
                case "dog":
                    pets.add(new Dog(name, age));
                    break;
                case "cat":
                    pets.add(new Cat(name, age));
                    break;
                case "hamster":
                    pets.add(new Hamster(name, age));
                    break;
                case "guinea":
                    pets.add(new GuineaPig(name, age));
                    break;
            }
        }

        pets.sort(Comparator.comparing(animal -> animal instanceof Omnivore));

        for (Animal pet : pets){
            System.out.println(pet);
        }
    }
}