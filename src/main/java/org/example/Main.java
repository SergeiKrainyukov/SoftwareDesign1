package org.example;

class Animal {
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

class Cat extends Animal {

    public void makeSound() {
        System.out.println("Meow");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat();
        cat.makeSound();
    }
}