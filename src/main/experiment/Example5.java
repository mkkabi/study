package main.experiment;

// "Конструктор 1"
// дальше - то, что определено методом toString()
public class Example5 {
    public static void main(String[] args) {
        System.out.println(new Human("Yura", 23));
    }
}

class Human {
    String name;
    int age;

    public Human() {

        System.out.println("Конструктор 1");
    }

    public Human(String name, int age) {
        this();
        this.name = name;
        this.age = age;
        if (age < 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
