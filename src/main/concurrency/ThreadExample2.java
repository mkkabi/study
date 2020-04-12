package main.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ThreadExample2 extends Thread {
    Exchanger exchenger;
    List<Integer> numbers = new ArrayList<>();
    int limit;
    public boolean flag = false;


    public ThreadExample2(Exchanger exchenger, String name, int threshold) {
    super(name);
        this.exchenger = exchenger;
        this.limit = threshold;
        this.start();
    }

    @Override
    public void run() {
        while(!flag){
            try {
               int var=(Integer) exchenger.exchange(0);
               numbers.add(var);
                System.out.println("current thread Name "+currentThread().getName()+ "sum of collection = " + arraySum());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(arraySum() >= limit){
                flag = true;
            }

        }
        System.out.println("Thread2 finished");
    }

    public int arraySum(){
        int result = 0;
        for (int i: numbers) {
            result += i;
        }
        return result;
    }
}
