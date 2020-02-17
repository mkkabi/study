package main;

import java.util.*;
import java.util.stream.Stream;

public class Start {

    public static void main(String[] args) {
        System.out.println("Running");

    }


    // 59 60 61  74 75 76  89 90 91  -55  128 35 67 83 94
    public int convert(int grade) {
        int result = 0;
        if (grade <= 60) {
            result = 2;
        } else if (grade < 75) {
            result = 3;
        } else if (grade < 90) {
            result = 4;
        } else {
            result = 5;
        }
        return result;
    }

    public int summa(int... slagaemoe) {
        int result = 0;
//        for (int i =0; i<slagaemoe.length; i++){
//            result+=slagaemoe[i];
//        }

        int resultFromStream = Arrays.stream(slagaemoe).sum();

        return resultFromStream;
    }


    public int multiplication(int... mnogiteli) throws IllegalArgumentException {
        if (mnogiteli.length == 0) {
            throw new IllegalArgumentException("invalid parameters length");
        } else if (mnogiteli.length == 1) {
            return mnogiteli[0];
        }

        int result[] = {1};
        Arrays.stream(mnogiteli).forEach(i -> {
            result[0] *= i;
        });
        return result[0];
    }
}
