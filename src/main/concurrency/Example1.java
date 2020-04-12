package main.concurrency;

import java.util.ArrayList;
import java.util.List;

public class Example1 {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> numbers = new ArrayList<>();
        ThreadGroup gr = new ThreadGroup("wqww");

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i > -1; i++) {
                System.out.println(Thread.currentThread().getName() + " i = " + i);
                numbers.add(i);
            }
            System.out.println(numbers + "  22222222");
        }, "thread 1");

//        System.out.println(thread1.getState()+" 1111111111");

//        thread1.start(); //
        // thread1.start();
//        System.out.println(thread1.getState());

        // thread1.join();

        List<Integer> testNumbers = new ArrayList<>();
        Thread2 thread21 = new Thread2(testNumbers, "thread 1", 10);
        Thread2 thread22 = new Thread2(testNumbers, "thread 2", 10);
        Thread2 thread23 = new Thread2(testNumbers, "thread 3", 10);
        System.out.println(testNumbers);

        testNumbers.stream().distinct().forEach(r -> {
            System.out.print(r + " " + testNumbers.stream().filter((n) -> n == r).count());
        });

        }
    }
