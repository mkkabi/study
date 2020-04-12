package main.concurrency;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class ThreadExample1 extends Thread {
Exchanger exchenger;

    int number = 0;
    int threshold;
    boolean flagStop=false;

    public ThreadExample1(int threshold,Exchanger exsc, String name) {
        super(name);
        this.threshold = threshold;
this.exchenger=exsc;
        this.start();
    }

    @Override
    public void run(){
        Random randomNumber = new Random();
        while(!flagStop) {
            this.number += randomNumber.nextInt(50);
            if(number >= threshold){
                System.out.println(this.getName() + " number = "+number);
                try {
                    Integer exchangeable = (Integer)exchenger.exchange(number);
                    if(exchangeable==null){
                        break;
                    }
                    number=(Integer)exchenger.exchange(number);
                    System.out.println("new NUmber  = " + number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Thread1 stopped");
    }

    public static void main(String[] args) {
        Exchanger<Integer>  exch=new Exchanger<>();
        ThreadExample1 t1 = new ThreadExample1(300, exch, "threadExample 1");
        ThreadExample2 t2 = new ThreadExample2(exch, "thread 2", 5000);



    }

}
