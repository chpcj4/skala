package skalajava;

public class AnimalSound {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();
        Animal tiger = new Tiger();
        
        dog.makeSound(); // 출력: 멍멍!
        cat.makeSound(); // 출력: 야옹!

        handleAnimalSound(tiger);
    }

    static void handleAnimalSound(Animal animal){
        animal.makeSound();
    }
}
