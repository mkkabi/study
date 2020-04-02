package main.experiment;

// ошибся в том, что не учел переопределение метода two() думал будет вызван непереопределенный метод родителя
// по факту ситуация такая:
// конструктор Student -> User -> User -> Overriden by Student two() -> Person.getPerson() и он еще не был инициализирован
// можно решить если объявить инициализацию Person статической
public class User {
    public User() {
        System.out.println("Work constructor User");
        two();
    }

    void one() {
        System.out.println("Work metod one() User");
    }

    void two() {
        System.out.println("Work metod two() User");
    }

}

class Student extends User {
    Person person = new Person();

    @Override
    void two() {
        System.out.println(person.getPerson());
    }

    public static void main(String[] args) {
        new Student();
    }
}

class Person {
    String getPerson() {
        return "Person";
    }
}