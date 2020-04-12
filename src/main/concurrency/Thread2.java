package main.concurrency;

import java.util.ArrayList;
import java.util.List;

public class Thread2 implements Runnable {
    List<Integer> numbers;
    int iteration;
    Thread  inner;

    public Thread2(List<Integer> numbers,String name,int iteration) {
        this.inner=new Thread(this,name);
        this.numbers = numbers;
        this.iteration = iteration;
        this.inner.start();
    }

    @Override
    public void run() {
        synchronized (numbers) {
            for (int i = 0; i < iteration; i++) {
                System.out.println(Thread.currentThread().getName() + " i = " + i);

                numbers.add(i);
            }
        }
        System.out.println("name " + Thread.currentThread().getName());
        System.out.println(numbers);


    }

    synchronized  void one(){

    }
}
