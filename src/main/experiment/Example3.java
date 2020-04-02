package main.experiment;

// Work static initializator
// Work initializator 1
// Work initializator 2
// если раскоментировать //System.out.println(Student2.count); то первой строчкой должно быть выведено 12
// про 12 в первой строке - не правильно, сначала вызывается статический инициализатор
public class Example3 {
    public static void main(String[] args) {
//        System.out.println(Student2.count);
        new Student2();
        //new Student2();

    }
}

class Student2 {
    static int count = 12;

    {
        System.out.println("Work initializator 1");
    }

    {
        System.out.println("Work initializator 2");
    }

    static {
        System.out.println("Work static initializator ");
    }
}
