package main;

import java.util.Arrays;
import java.util.List;

public class HomeWork {

    public int multiplication(int... mnogiteli) throws IllegalArgumentException {
        // Если не было введено ни одного параметра, выбрасывается исключение
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


    public static <T extends Comparable> List<T> bubbleSortArrayList(List<T> inputList) {
        int n = inputList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if ((inputList.get(j).compareTo(inputList.get(j + 1))) > 0) {
                    T temp = inputList.get(j);
                    inputList.set(j, inputList.get(j + 1));
                    inputList.set(j + 1, temp);
                }
            }
        }
        return inputList;
    }

}
