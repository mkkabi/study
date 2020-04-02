package main.experiment;

// Что будет выведено на консоль и почему.
// в консоль должно быть выведено: "Работает конструктор Fruit" \n "Работает конструктор Orange"
// да, так и есть. ну еще было сообщение Process finished with exit code 0
public class Example1 {
    public static void main(String[] args) {
        new Orange();
    }
}

class Fruit {
    String sort;
    int weight;

    public Fruit() {
        System.out.println("Работает конструктор Fruit");
    }
}

class Orange extends Fruit {
    public Orange() {
        System.out.println("Работает конструктор Orange");
    }

}