package main.experiment;

/* тоже правильно
    Work initializator Fruit1
    Работает конструктор Fruit1
    Работает конструктор Orange1
        Work metod one() Fruit1
        Work metod one() Fruit1
        Work metod one() Fruit1 до stack overflow
    * */
public class Example2 {
    public static void main(String[] args) {
        new Orange1();
    }

}

class Fruit1 {
    String sort;
    int weight;

    {
        System.out.println("Work initializator Fruit1");
    }

    public Fruit1() {
        System.out.println("Работает конструктор Fruit1");
    }

    void one() {
        System.out.println("Work metod one() Fruit1");
        one();
    }

}

class Orange1 extends Fruit1 {
    public Orange1() {
        System.out.println("Работает конструктор Orange1");
        super.one();
    }



}
